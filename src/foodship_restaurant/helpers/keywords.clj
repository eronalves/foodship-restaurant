(ns foodship-restaurant.helpers.keywords
  (:require [clojure.string :as str]))

(declare transform-keywords)

(defn remove-slash [keys]
  (map 
    (fn [key] 
      (let [splitted (str/split (name key) #"/")]
        (keyword (last splitted))))
    keys))

(defn reduce-keywords [coll]
  (zipmap (remove-slash (keys coll)) (vals coll)))

(defn update-map [m f] 
  (reduce-kv (fn [m k v] 
    (assoc m k (f v))) {} m))

(defn reduce-inner-maps [map]
  (update-map map transform-keywords))

(defn reduce-map-keywords [map]
  (->> map
    (reduce-keywords)
    (reduce-inner-maps)))

(defn reduce-coll-keywords [coll]
  (map #(transform-keywords %) coll))

(defn transform-keywords [val]
  (cond 
    (map? val) (reduce-map-keywords val)
    (coll? val) (reduce-coll-keywords val)
    :else val))