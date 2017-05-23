;
;  (C) Copyright 2017  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns clj-adoc2docbook.adoc2docbook)


(require '[clojure.string          :as str])

(require '[cogset-common.utils :refer :all])

(defn escape-xml-chars
    [string]
    (str/escape string
        {\< "&lt;", \> "&gt;", \& "&amp;"}))

; Some special characters have special meaning:
; <systemitem> - rendered as bold, suggest using * (asterisk)
; <package>    - rendered as italic, suggest using _ (underscore)
; <literal>    - rendered as bold, suggest using ` (backtick)
; <command>    - rendered as bold, sugget using " (double quote)
(defn asciidoc-like-transformation
    [link-to-bugzilla string]
    ; TODO - just a quick hack, would be better to replace with proper state machine
    (-> string
        ; special handling for words at the beginning of line
        ;(str/replace #"^'([^\s]+\s*[^\s']*)'([ .,])"    #(str "<literal>"    (second %1) "</literal>" (nth %1 2)))
        (str/replace #"^`([^\s]+\s*[^\s`]*)`([ .,(])"    #(str "<literal>"    (second %1) "</literal>" (nth %1 2)))
        (str/replace #"^\"([^\s]+\s*[^\s\"]*)\"([ .,])" #(str "<command>"    (second %1) "</command>" (nth %1 2)))
        (str/replace #"^\*([^\s]+\s*[^\s\*]*)\*([ .,])" #(str "<systemitem>" (second %1) "</systemitem>" (nth %1 2)))
        (str/replace #"^_([^\s]+\s*[^\s\_]*)_([ .,])"   #(str "<package>"    (second %1) "</package>" (nth %1 2)))

        ; words in the middle - should start with <space> and end with <space>, <dot>, <comma>, or <parathesis>
        ;(str/replace  #" '([^\s]+(\s+[^\s']+)*)'([ .,)])"    #(str " <literal>"    (second %1) "</literal>" (nth %1 3)))
        (str/replace  #"([ (])`([^\s]+(\s+[^\s`]+)*)`([ .,()])"    #(str (second %1) "<literal>" (nth %1 2) "</literal>" (nth %1 4)))
        (str/replace #" \"([^\s]+(\s+[^\s\"]+)*)\"([ .,)])"   #(str " <command>"     (second %1) "</command>" (nth %1 3)))
        (str/replace #" \*([^\s]+(\s+[^\s\*]+)*)\*([ .,\-)])"   #(str " <systemitem>"  (second %1) "</systemitem>" (nth %1 3)))
        (str/replace  #" _([^\s]+(\s+[^\s_]+)*)_([ .,:)])"    #(str " <package>"    (second %1) "</package>" (nth %1 3)))

        ; special handling for words at end of line
        ;(str/replace  #" '([^\s]+\s*[^\s']*\s*[^\s']*)'$"    #(str " <literal>"    (second %1) "</literal>"))
        (str/replace  #" `([^\s]+\s*[^\s`]*\s*[^\s`]*)`$"    #(str " <literal>"    (second %1) "</literal>"))
        (str/replace #" \"([^\s]+\s*[^\s\"]*\s*[^\s\"]*)\"$" #(str " <command>"    (second %1) "</command>"))
        (str/replace #" \*([^\s]+\s*[^\s\*]*\s*[^\s\*]*)\*$" #(str " <systemitem>" (second %1) "</systemitem>"))
        (str/replace  #" _([^\s]+\s*[^\s_]*\s*[^\s_]*)_$"    #(str " <package>"    (second %1) "</package>"))

        ; special cases
        (str/replace  #"\(_([^\s]+(\s+[^\s_]+)*)_\)"    #(str "(<package>"    (second %1) "</package>)"))

        ; for situations when whole line is in backticks
        (str/replace  #"^`([^`]+)`$"    #(str "<literal>" (second %1) "</literal>"))

        ; BZ#123456 should be rendered as <ulink>
        (str/replace #"([^,]) (BZ#[0-9]+)" #(str (second %1) " <ulink url='" link-to-bugzilla (subs (third %1) 3) "'>" (third %1) "</ulink>"))))

(defn get-line-type
    "There exist four line types, according to the specification:
     1: normal lines
     2: lines for the <screen> environment
     3: lines for the <itemizedlist> environment
     4: empty lines that should be ignored when not in <screen> environment"
    [line]
    (cond (empty? line)                   :empty
          (empty? (str/trim line))        :empty
          (re-matches #"\s*\*\s+.*" line) :item   ; !!!order is important!!!
          (.startsWith line "   ")        :screen ; 3 or more spaces
          :else                           :normal
    ))

(defn list-item
    [link-to-bugzilla line]
    (let [star-index (inc (.indexOf line "*"))
          content    (str/trim (subs line star-index))]
        (str "    <listitem><para>" (asciidoc-like-transformation link-to-bugzilla content) "</para></listitem>\n")))

(defn screen-list-item-begin
    [line]
    (str "</itemizedlist>\n\n<screen>\n" line))

(defn screen-list-item-end
    [line]
    (let [star-index (inc (.indexOf line "*"))
          content    (str/trim (subs line star-index))]
        (str "\n</screen>\n\n<itemizedlist>\n    <listitem><para>" content "</para></listitem>\n")))

(defn start-para
    [link-to-bugzilla line]
    ["<para>"
     (asciidoc-like-transformation link-to-bugzilla line)])

(defn middle-in-para
    [link-to-bugzilla line]
    [" " (asciidoc-like-transformation link-to-bugzilla line)])

(defn end-para
    []
    ["</para>\n"
     "\n"])

(defn first-list-item
    [link-to-bugzilla line]
    ["<itemizedlist>\n"
     (list-item link-to-bugzilla line)])

(defn end-para-first-list-item
    [link-to-bugzilla line]
    ["</para>\n"
     "\n"
     "<itemizedlist>\n"
     (list-item link-to-bugzilla line)])

(defn last-list-item
    [link-to-bugzilla line]
    ["</itemizedlist>\n"
     "\n"
     "<para>"
     (asciidoc-like-transformation link-to-bugzilla line)])

(defn screen
    [line]
    (str/trim line))

(defn screen-notrim
    [line]
    (cond
        (.startsWith line "    ") (subs line 4)
        (.startsWith line "   ") (subs line 3)
        :else (str/trim line)))

(defn screen-empty
    [line]
    ["\n"
     (screen line)
     "\n"])

(defn screen-begin
    [line]
    ["<screen>\n"
     (screen line)
     "\n"])

(defn end-para-screen-begin
    [line]
    ["</para>\n"
     "\n"
     "<screen>\n"
     (screen line)
     "\n"])

(defn screen-in-middle
    [line]
    [(screen line)
     "\n"])

(defn screen-in-middle-notrim
    [line]
    [(screen-notrim line)
     "\n"])

(defn screen-end
    [link-to-bugzilla line]
    ["</screen>\n"
     "\n"
     "<para>"
     (screen (asciidoc-like-transformation link-to-bugzilla line))])

(defn get-output
    [link-to-bugzilla line last-status]
    (condp = [last-status (get-line-type line)]
        [:nothing         :empty]    [:nothing         nil] ; ignore
        [:nothing         :normal]   [:paragraph       (start-para link-to-bugzilla line)]
        [:nothing         :item]     [:itemized-list   (first-list-item link-to-bugzilla line)]
        [:nothing         :screen]   [:screen          (screen-begin line)]
        [:paragraph       :normal]   [:paragraph       (middle-in-para link-to-bugzilla line)]
        [:paragraph       :empty]    [:nothing         (end-para)]
        [:paragraph       :item]     [:itemized-list   (end-para-first-list-item link-to-bugzilla line)]
        [:paragraph       :screen]   [:screen          (end-para-screen-begin line)]
        [:itemized-list   :item]     [:itemized-list   (list-item link-to-bugzilla line)]
        [:itemized-list   :empty]    [:itemized-list   nil] ;ignore
        [:itemized-list   :normal]   [:paragraph       (last-list-item link-to-bugzilla line)]
        [:itemized-list   :screen]   [:itemized-screen (screen-list-item-begin line)]
        [:itemized-screen :screen]   [:itemized-screen (screen-in-middle line)]
        [:itemized-screen :item]     [:itemized-list   (screen-list-item-end line)]
        [:itemized-screen :empty]    [:itemized-screen nil]
        [:itemized-screen :normal]   [:paragraph       (screen-end line)]
        [:screen          :screen]   [:screen          (screen-in-middle-notrim line)]
        [:screen          :normal]   [:paragraph       (screen-end link-to-bugzilla line)]
        [:screen          :empty]    [:screen-empty    nil]
        [:screen-empty    :screen]   [:screen          (screen-empty line)]
        [:screen-empty    :normal]   [:paragraph       (screen-end link-to-bugzilla line)]
        [:screen-empty    :empty]    [:screen-empty    ""]
        [last-status line]))

(defn closing-tag
    [status]
    (condp = status
        :nothing         "\n"
        :screen          "</screen>\n"
        :paragraph       "</para>\n"
        :itemized-list   "</itemizedlist>\n"
        :itemized-screen "</screen>\n"))

(def http-regexp
    ; scheme     host name                             domain       query+fragment               we don't want those chars at the end of URL
    #"(https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)[^?.,!) ])")

(def bz-href-regexp
    #"BZ####([0-9]+)")

(defn create-normal-links
    [string]
    (let [href (re-find http-regexp string)]
        (if href
            (str/replace string http-regexp #(str "<ulink url='" (first %1) "'>" (first %1) "</ulink>"))
            string)))

(defn create-bugzilla-links
    [link-to-bugzilla string]
    (let [bz-href (re-find bz-href-regexp string)]
        (if bz-href
            (str/replace string bz-href-regexp #(str "BZ#<ulink url='" link-to-bugzilla (second %1) "'>" (second %1) "</ulink>"))
            string)))

(defn create-links
    [link-to-bugzilla string]
    (->> string
         create-normal-links
         (create-bugzilla-links link-to-bugzilla)))

(defn format-one-line
    "Format paragraph(s) and replace sequence of two newline characters by
     a new paragraph."
    [link-to-bugzilla line]
    (->> line escape-xml-chars
         (create-links link-to-bugzilla)))

(defn format-all-lines
    [link-to-bugzilla lines]
    (map #(format-one-line link-to-bugzilla %) lines))

(defn transform-lines
    [link-to-bugzilla lines]
    (loop [result [] status :nothing lines lines]
        (let [line                (first lines)
              [new-status output] (get-output link-to-bugzilla line status)
              rest                (next lines)
              new-result  (if output (if (vector? output)
                                         (into result output)
                                         (conj result output))
                                    result)]
              ;(println status  (get-line-type line) new-status line)
              (if rest
                  (recur new-result
                         new-status rest)
                  (conj new-result (closing-tag new-status))))))

