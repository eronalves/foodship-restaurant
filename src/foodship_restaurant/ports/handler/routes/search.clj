(ns foodship-restaurant.ports.handler.routes.search
  (:require 
    [compojure.route :as route]
    [ring.util.http-response :refer :all]
    [compojure.api.sweet :refer :all]
    [schema.core :as s]
    [foodship-restaurant.domain.controller :as controller]
    [foodship-restaurant.helpers.keywords :refer [transform-keywords]]))

(defn context-routes []
  (context "/restaurants" []
    :tags ["restaurants"]

    (GET "/" []
      :components [domain-controller]
      :query-params [name :- String, tags :- [String]]
      (println name tags)
      (ok (controller/restaurants domain-controller name tags)))

    (context "/:id" []
      :path-params [id :- s/Int]

      (GET "/" []
        :components [domain-controller]
        (ok (transform-keywords (controller/get-restaurant domain-controller id)))))))