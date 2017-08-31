(ns foodship-restaurant.domain.domain-functions.filter-restaurants
  (:require [clojure.string :as s]))

(defn- by-name [restaurant name]
  (if (not (nil? name))
    (s/includes? (:restaurant/name restaurant) name)
    true))

(defn- by-tags [restaurant tags]
  (if (not (empty? tags))
    (> (count (clojure.set/intersection (set (:restaurant/tags restaurant)) (set tags))) 0)
    true))

(defn by-name-and-tags [restaurants name tags]
  (filter #(and (by-name % name) (by-tags % tags)) restaurants))