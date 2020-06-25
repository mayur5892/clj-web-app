(defproject clj-web-app "0.1.0-SNAPSHOT"
  :description "sample application for exposing rest-api in Clojure"
  :url "http://example.com/FIXME"
  :main clj-web-app.main
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [yada "1.3.0-alpha9"]]
  :repl-options {:init-ns clj-web-app.main})
