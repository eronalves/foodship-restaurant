(ns foodship-restaurant.domain.controller
  (:require 
    [foodship-restaurant.adapters.db-adapter :as db]
    [foodship-restaurant.domain.domain-functions.filter-restaurants :as filter-restaurants]))

(defn get-restaurant [component id]
  (db/retrieve (:db component) id))

(defn restaurants [component name tags]
  (filter-restaurants/by-name-and-tags (db/all (:db component)) name tags))