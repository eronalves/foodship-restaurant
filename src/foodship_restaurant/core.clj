(ns foodship-restaurant.core
  (:gen-class)
  (:require 
   [com.stuartsierra.component :as component]
   [foodship-restaurant.systems :refer [prod-system]]))

(defn -main
  "Start a production system."
  [& args]
  (if (nil? (first args))
    (throw (ex-info "Provide the http port on first argument when executing production enviorment" {}))
    (component/start (prod-system (read-string (first args))))))