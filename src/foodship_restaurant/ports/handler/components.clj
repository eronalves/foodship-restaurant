(ns foodship-restaurant.ports.handler.components
  (:require 
    [com.stuartsierra.component :as component]
    [compojure.api.sweet :refer :all]))

(defmethod compojure.api.meta/restructure-param :components
  [_ components acc]
  (update-in acc [:letks] into [components `(::components ~'+compojure-api-request+)]))

(defn wrap-components [handler components]
  (fn [req]
    (handler (assoc req ::components components))))

(defn make-handler
  [component]
  (require 'foodship-restaurant.ports.handler.routes.app)
  (let [deps (select-keys component (:deps component))]
    (-> (resolve 'foodship-restaurant.ports.handler.routes.app/app-routes)
        (wrap-components deps))))