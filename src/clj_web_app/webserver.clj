(ns clj-web-app.webserver
  (:require [yada.yada :as yada]
            [clj-web-app.book-inventory :as b]
            [clojure.tools.logging :as log]
            [schema.core :as s]))


(defn app-routes []
  ["/"
   [["" (yada/resource {:methods
                        {:get {:produces "application/json"
                               :response (fn [_]
                                           {:message "Welcome to Clojure web server"})}}})]
    ["books"
     [["" (yada/resource {:methods
                          {:post {:consumes "application/json"
                                  :parameters {:body {:id s/Num
                                                      :title s/Str
                                                      :author s/Str
                                                      :prize s/Num}}
                                  :response (fn [ctx]
                                              (b/add-book ctx))}

                           :get {:produces "application/json"
                                 :response (fn [_]
                                             (b/fetch-all-books))}}})]
      [["/" :book-id]
       [["" (yada/resource {:methods
                            {:get {:produces "application/json"
                                   :parameters {:path {:book-id Long}}
                                   :response (fn [ctx]
                                               (if-let [book (b/fetch-book-by-id (-> ctx :parameters :path :book-id))]
                                                 book
                                                 (assoc (:response ctx) :status 404 :body "No book found with provided ID")))}

                             :put {:consumes "application/json"
                                   :parameters {:path {:book-id Long}
                                                :body {:title s/Str
                                                       :author s/Str
                                                       :prize s/Num}}
                                   :response (fn [ctx]
                                               (b/update-book ctx))}

                             :delete {:parameters {:path {:book-id Long}}
                                      :response (fn [ctx]
                                                  (b/delete-book ctx))}}})]]]]]]])

(defn  start-server []
  (let [server-config (yada/listener (app-routes)
                                {:port 8032})]
    (log/info "server started in port %s" (:port server-config))
    (println "server started in port" (:port server-config))
    (println "server can be accessed from http://localhost:8032")))
