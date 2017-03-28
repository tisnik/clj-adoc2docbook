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

(defproject org.clojars.tisnik/clj-adoc2docbook "0.1.0-SNAPSHOT"
    :description "Library for asciidoc to docbook conversion"
    :url "https://github.com/tisnik/clj-jenkins-api"
    :license {:name "Eclipse Public License"
              :url "http://www.eclipse.org/legal/epl-v10.html"}
    :dependencies [[org.clojure/clojure "1.7.0"]]
    :plugins [[test2junit "1.1.0"]
              [codox "0.8.11"]
              [lein-cloverage "1.0.6"]]
    :target-path "target/%s"
    :profiles {:uberjar {:aot :all}})