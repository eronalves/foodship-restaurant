(ns foodship-restaurant.ports.handler.routes.restaurant
  (:require 
    [compojure.route :as route]
    [ring.util.http-response :refer :all]
    [schema.core :as s]
    [compojure.api.sweet :refer :all]
    [foodship-restaurant.ports.handler.routes.route-schemas :as schemas]
    [foodship-restaurant.domain.controller :as controller]))

(defn context-routes []
  (context "/restaurants" []
    :tags ["restaurants"]

    (GET "/" []
      :components [domain-controller]
      :query [filter schemas/FilterData]
      (ok (controller/restaurants domain-controller (:name filter) (:tags filter))))

    (POST "/" []
      :components [domain-controller]
      :body [restaurant schemas/CreateAlterRestaurant]
      :summary "Create a restaurant"
      (ok (controller/create-restaurant domain-controller restaurant)))

    (context "/:id" []
      :path-params [id :- s/Int]

      (GET "/" []
        :components [domain-controller]
        (ok (controller/get-restaurant domain-controller id)))

      (POST "/" []
        :components [domain-controller]
        :body [restaurant schemas/CreateAlterRestaurant]
        :summary "Update a restaurant"
        (ok (controller/update-restaurant domain-controller id restaurant))))))