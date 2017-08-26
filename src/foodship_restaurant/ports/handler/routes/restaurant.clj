(ns foodship-restaurant.ports.handler.routes.restaurant
  (:require 
    [compojure.route :as route]
    [ring.util.http-response :refer :all]
    [compojure.api.sweet :refer :all]
    [schema.core :as s]
    [foodship-restaurant.domain.controller :as controller]))

(defn context-routes []
  (context "/restaurant/:id" []
    :path-params [id :- s/Int]
    :tags ["restaurant"]

    (GET "/" []
      :components [domain-controller]
      (ok (controller/get-restaurant domain-controller id)))))