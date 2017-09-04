(ns foodship-restaurant.adapters.db-adapter
  (:require 
    [foodship-restaurant.ports.db.mapgraph :as mapgraph]
    [foodship-restaurant.helpers.keywords :as keywords]))

(defn retrieve [component id]
  (keywords/transform-keywords
    (mapgraph/get-restaurant (:memory-db component) id)))

(defn all [component]
  (keywords/transform-keywords
    (mapgraph/all (:memory-db component))))

