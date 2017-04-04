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

(ns clj-adoc2docbook.adoc2docbook-test
  (:require [clojure.test :refer :all]
            [clj-adoc2docbook.adoc2docbook :refer :all]))

;
; Common functions used by tests.
;

(defn callable?  "Test if given function-name is bound to the real function."
    [function-name]
    (clojure.test/function? function-name))

;
; Tests for existence of various defs and functions
;

(deftest test-escape-xml-chars-existence
    "Check that the clj-adoc2docbook.adoc2docbook/escape-xml-chars definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/escape-xml-chars definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/escape-xml-chars))))


(deftest test-asciidoc-like-transformation-existence
    "Check that the clj-adoc2docbook.adoc2docbook/asciidoc-like-transformation definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/asciidoc-like-transformation definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/asciidoc-like-transformation))))


(deftest test-get-line-type-existence
    "Check that the clj-adoc2docbook.adoc2docbook/get-line-type definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/get-line-type definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/get-line-type))))


(deftest test-list-item-existence
    "Check that the clj-adoc2docbook.adoc2docbook/list-item definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/list-item definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/list-item))))


(deftest test-screen-list-item-begin-existence
    "Check that the clj-adoc2docbook.adoc2docbook/screen-list-item-begin definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/screen-list-item-begin definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/screen-list-item-begin))))


(deftest test-screen-list-item-end-existence
    "Check that the clj-adoc2docbook.adoc2docbook/screen-list-item-end definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/screen-list-item-end definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/screen-list-item-end))))


(deftest test-start-para-existence
    "Check that the clj-adoc2docbook.adoc2docbook/start-para definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/start-para definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/start-para))))


(deftest test-middle-in-para-existence
    "Check that the clj-adoc2docbook.adoc2docbook/middle-in-para definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/middle-in-para definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/middle-in-para))))


(deftest test-end-para-existence
    "Check that the clj-adoc2docbook.adoc2docbook/end-para definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/end-para definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/end-para))))


(deftest test-first-list-item-existence
    "Check that the clj-adoc2docbook.adoc2docbook/first-list-item definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/first-list-item definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/first-list-item))))


(deftest test-end-para-first-list-item-existence
    "Check that the clj-adoc2docbook.adoc2docbook/end-para-first-list-item definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/end-para-first-list-item definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/end-para-first-list-item))))


(deftest test-last-list-item-existence
    "Check that the clj-adoc2docbook.adoc2docbook/last-list-item definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/last-list-item definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/last-list-item))))


(deftest test-screen-existence
    "Check that the clj-adoc2docbook.adoc2docbook/screen definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/screen definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/screen))))


(deftest test-screen-notrim-existence
    "Check that the clj-adoc2docbook.adoc2docbook/screen-notrim definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/screen-notrim definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/screen-notrim))))


(deftest test-screen-empty-existence
    "Check that the clj-adoc2docbook.adoc2docbook/screen-empty definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/screen-empty definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/screen-empty))))


(deftest test-screen-begin-existence
    "Check that the clj-adoc2docbook.adoc2docbook/screen-begin definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/screen-begin definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/screen-begin))))


(deftest test-end-para-screen-begin-existence
    "Check that the clj-adoc2docbook.adoc2docbook/end-para-screen-begin definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/end-para-screen-begin definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/end-para-screen-begin))))


(deftest test-screen-in-middle-existence
    "Check that the clj-adoc2docbook.adoc2docbook/screen-in-middle definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/screen-in-middle definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/screen-in-middle))))


(deftest test-screen-in-middle-notrim-existence
    "Check that the clj-adoc2docbook.adoc2docbook/screen-in-middle-notrim definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/screen-in-middle-notrim definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/screen-in-middle-notrim))))


(deftest test-screen-end-existence
    "Check that the clj-adoc2docbook.adoc2docbook/screen-end definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/screen-end definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/screen-end))))


(deftest test-get-output-existence
    "Check that the clj-adoc2docbook.adoc2docbook/get-output definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/get-output definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/get-output))))


(deftest test-closing-tag-existence
    "Check that the clj-adoc2docbook.adoc2docbook/closing-tag definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/closing-tag definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/closing-tag))))


(deftest test-create-normal-links-existence
    "Check that the clj-adoc2docbook.adoc2docbook/create-normal-links definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/create-normal-links definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/create-normal-links))))


(deftest test-create-bugzilla-links-existence
    "Check that the clj-adoc2docbook.adoc2docbook/create-bugzilla-links definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/create-bugzilla-links definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/create-bugzilla-links))))


(deftest test-create-links-existence
    "Check that the clj-adoc2docbook.adoc2docbook/create-links definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/create-links definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/create-links))))


(deftest test-format-one-line-existence
    "Check that the clj-adoc2docbook.adoc2docbook/format-one-line definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/format-one-line definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/format-one-line))))


(deftest test-format-all-lines-existence
    "Check that the clj-adoc2docbook.adoc2docbook/format-all-lines definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/format-all-lines definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/format-all-lines))))


(deftest test-transform-lines-existence
    "Check that the clj-adoc2docbook.adoc2docbook/transform-lines definition exists."
    (testing "if the clj-adoc2docbook.adoc2docbook/transform-lines definition exists."
        (is (callable? 'clj-adoc2docbook.adoc2docbook/transform-lines))))

;
; Regular tests
;

(deftest test-escape-xml-chars
    (testing "escape-xml-chars"
        (is (= ""             (escape-xml-chars "")))
        (is (= " "            (escape-xml-chars " ")))
        (is (= "x"            (escape-xml-chars "x")))
        (is (= "\n"           (escape-xml-chars "\n")))
        (is (= "&lt;"         (escape-xml-chars "<")))
        (is (= "&gt;"         (escape-xml-chars ">")))
        (is (= "&amp;"        (escape-xml-chars "&")))
        (is (= "x&lt;"        (escape-xml-chars "x<")))
        (is (= "x&gt;"        (escape-xml-chars "x>")))
        (is (= "x&amp;"       (escape-xml-chars "x&")))
        (is (= "&lt;x"        (escape-xml-chars "<x")))
        (is (= "&gt;x"        (escape-xml-chars ">x")))
        (is (= "&amp;x"       (escape-xml-chars "&x")))
        (is (= "x&lt;x"       (escape-xml-chars "x<x")))
        (is (= "x&gt;x"       (escape-xml-chars "x>x")))
        (is (= "x&amp;x"      (escape-xml-chars "x&x")))
        (is (= "&lt;x&lt;x"   (escape-xml-chars "<x<x")))
        (is (= "&lt;x&gt;x"   (escape-xml-chars "<x>x")))
        (is (= "&lt;x&amp;x"  (escape-xml-chars "<x&x")))
        (is (= "&gt;x&lt;x"   (escape-xml-chars ">x<x")))
        (is (= "&gt;x&gt;x"   (escape-xml-chars ">x>x")))
        (is (= "&gt;x&amp;x"  (escape-xml-chars ">x&x")))
        (is (= "&amp;x&lt;x"  (escape-xml-chars "&x<x")))
        (is (= "&amp;x&gt;x"  (escape-xml-chars "&x>x")))
        (is (= "&amp;x&amp;x" (escape-xml-chars "&x&x")))))

(deftest test-create-links-no-replace
    (testing "create-links function"
        (is (= "" (create-links "http://test.org" "")))
        (is (= "http:"    (create-links "http://test.org" "http:")))
        (is (= "https:"   (create-links "http://test.org" "https:")))
        (is (= "http://"  (create-links "http://test.org" "http://")))
        (is (= "https://" (create-links "http://test.org" "https://")))))

(deftest test-create-links-replace
    (testing "create-links function"
        (is (= "" (create-links "http://test.org" "")))
        (is (= "<ulink url='http://www.redhat.com'>http://www.redhat.com</ulink>"   (create-links "http://test.org" "http://www.redhat.com")))
        (is (= "<ulink url='https://www.redhat.com'>https://www.redhat.com</ulink>" (create-links "http://test.org" "https://www.redhat.com")))
        (is (= "<ulink url='http://www.fedora.cz'>http://www.fedora.cz</ulink>"     (create-links "http://test.org" "http://www.fedora.cz")))
        (is (= "<ulink url='https://www.fedora.cz'>https://www.fedora.cz</ulink>"   (create-links "http://test.org" "https://www.fedora.cz")))))

(deftest test-create-links-replace-slash-at-end
    (testing "create-links function"
        (is (= "" (create-links "http://test.org" "")))
        (is (= "<ulink url='http://www.redhat.com/'>http://www.redhat.com/</ulink>"   (create-links "http://test.org" "http://www.redhat.com/")))
        (is (= "<ulink url='https://www.redhat.com/'>https://www.redhat.com/</ulink>" (create-links "http://test.org" "https://www.redhat.com/")))
        (is (= "<ulink url='http://www.fedora.cz/'>http://www.fedora.cz/</ulink>"     (create-links "http://test.org" "http://www.fedora.cz/")))
        (is (= "<ulink url='https://www.fedora.cz/'>https://www.fedora.cz/</ulink>"   (create-links "http://test.org" "https://www.fedora.cz/")))))

