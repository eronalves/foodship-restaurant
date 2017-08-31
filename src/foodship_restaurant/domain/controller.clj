(ns foodship-restaurant.domain.controller
  (:require [foodship-restaurant.adapters.db-adapter :as db]))

(defn get-restaurant [component id]
  (db/retrieve (:db component) id))

(defn restaurants [component name tags]
  (println "chegou")
  (db/all (:db component)))