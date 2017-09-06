(ns foodship-restaurant.adapters.db-adapter
  (:require 
    [foodship-restaurant.ports.db.memory_db :as memory-db]
    [foodship-restaurant.helpers.keywords :as keywords]))

(defn retrieve-restaurant [component id]
  (keywords/transform-keywords
    (memory-db/get-restaurant (:memory-db component) id)))

(defn all-restaurants [component]
  (keywords/transform-keywords
    (memory-db/all (:memory-db component))))

(defn create-restaurant! [component restaurant]
  (memory-db/insert-restaurant! (:memory-db component) restaurant))

(defn update-restaurant! [component restaurant]
  (memory-db/insert-restaurant! (:memory-db component) restaurant))

