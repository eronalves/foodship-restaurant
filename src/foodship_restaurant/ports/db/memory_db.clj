(ns foodship-restaurant.ports.db.memory_db
  (:require 
    [com.stuartsierra.mapgraph :as mg]
    [com.stuartsierra.component :as component]
    [clojure.string :as s]
    [foodship-restaurant.ports.db.data.initial-data :as initial-data]))

(defn retrieve-restaurant [component id]
  (mg/pull @(:db component) 
    [:restaurant/id]
    [:restaurant/id id]))

(defn get-restaurant [component id]
  (get @(:db component) [:restaurant/id id]))

(defn all [component]
  (vals 
    (filter 
      (fn [[k v]] (s/includes? k "restaurant/id")) 
      @(:db component))))

(defn insert-restaurant! [component restaurant]
  (dosync
    (alter (:db component) mg/add restaurant)
    (retrieve-restaurant component (:id restaurant))))

(defrecord MapGraphDB [db]
  component/Lifecycle
  (start [this]
    (dosync
      (alter db mg/add-id-attr :restaurant/id)
      (initial-data/setup this insert-restaurant!))
    this)

  (stop [this]
    (assoc this :db nil)
    this))

(defn create []
  (map->MapGraphDB {:db (ref (mg/new-db)) }))