(ns foodship-restaurant.adapters.db-adapter
  (:require 
    [foodship-restaurant.ports.db.mapgraph :as mapgraph]))

(defprotocol IDatabase
  (retrieve [this id] "Retrieve a restaurant from repository"))

(defrecord DBAdapter [memory-db]
  IDatabase
  (retrieve [this id]
    (mapgraph/retrieve-restaurant (:memory-db this) id)))
    
(defn create []
  (map->DBAdapter {}))