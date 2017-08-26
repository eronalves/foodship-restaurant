(ns foodship-restaurant.ports.db.mapgraph
  (:require 
    [com.stuartsierra.mapgraph :as mg]
    [com.stuartsierra.component :as component]))

(defn- add-restaurant [restaurants restaurant]
  (conj restaurants restaurant))

(defn- create-restaurant-if-no-exists [id restaurant]
  (if (nil? restaurant)
    {:restaurant/id id}
    restaurant))

(defprotocol IMapGraphDB
  (retrieve-restaurant [this id] "Retrieve a restaurant from mapgraph"))

(defrecord MapGraphDB [db]
  component/Lifecycle
  (start [this]
    (dosync
      (alter db mg/add-id-attr :restaurant/id))
    this)

  (stop [this]
    (assoc this :db nil)
    this)
    
  IMapGraphDB
  (retrieve-restaurant [this id]
    (mg/pull @(:db this) 
      [:restaurant/id]
      [:restaurant/id id])))

(defn create []
  (map->MapGraphDB {:db (ref (mg/new-db)) }))