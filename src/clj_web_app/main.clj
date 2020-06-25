(ns clj-web-app.main
  (:require [clj-web-app.webserver :refer [start-server]]))



(defn -main
  []
  (start-server))
