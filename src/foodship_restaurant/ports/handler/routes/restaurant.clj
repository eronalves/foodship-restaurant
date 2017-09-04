(ns foodship-restaurant.ports.handler.routes.restaurant
  (:require 
    [compojure.route :as route]
    [ring.util.http-response :refer :all]
    [compojure.api.sweet :refer :all]
    [schema.core :as s]
    [foodship-restaurant.domain.controller :as controller]))

(s/defschema FilterData 
  {(s/optional-key :name) s/Str
   (s/optional-key :tags) [s/Str]})

(defn context-routes []
  (context "/restaurants" []
    :tags ["restaurants"]

    (GET "/" []
      :components [domain-controller]
      :query [filter FilterData]
      (ok (controller/restaurants domain-controller (:name filter) (:tags filter))))

    (context "/:id" []
      :path-params [id :- s/Int]

      (GET "/" []
        :components [domain-controller]
        (ok (controller/get-restaurant domain-controller id))))))