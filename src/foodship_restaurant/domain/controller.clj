(ns foodship-restaurant.domain.controller
  (:require 
    [foodship-restaurant.adapters.db-adapter :as db]
    [foodship-restaurant.domain.domain-functions.helper-id :as helper-id]
    [foodship-restaurant.domain.domain-functions.filter-restaurants :as filter-restaurants]))

(defn get-restaurant [component id]
  (db/retrieve-restaurant (:db component) id))

(defn restaurants [component name tags]
  (filter-restaurants/by-name-and-tags (db/all-restaurants (:db component)) name tags))

(defn create-restaurant! [component restaurant]
  (let [new-restaurant (assoc restaurant :id (helper-id/generate-id "restaurant"))]
    (db/create-restaurant! (:db component) new-restaurant)
    (get-restaurant component (:id new-restaurant))))

(defn update-restaurant! [component id restaurant]
  (let [retrieved-restaurant (get-restaurant component id)]
    (db/update-restaurant! (:db component) (merge retrieved-restaurant restaurant))
    (get-restaurant component id)))