(ns foodship-restaurant.adapters.db-adapter
  (:require 
    [foodship-restaurant.ports.db.mapgraph :as mapgraph]))

(defn retrieve [component id]
  (mapgraph/get-restaurant (:memory-db component) id))

(defn all [component]
  (println "chegou 2")
  (mapgraph/all (:memory-db component)))

