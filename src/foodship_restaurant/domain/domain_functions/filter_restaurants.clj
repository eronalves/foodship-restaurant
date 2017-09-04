(ns foodship-restaurant.domain.domain-functions.filter-restaurants
  (:require [clojure.string :as s]))

(defn- deaccent [str]
  "Remove accent from string"
  (let [normalized (java.text.Normalizer/normalize str java.text.Normalizer$Form/NFD)]
    (clojure.string/replace normalized #"\p{InCombiningDiacriticalMarks}+" "")))

(defn- by-name [restaurant name]
  (if (not (nil? name))
    (s/includes? 
      (clojure.string/upper-case
        (deaccent (:name restaurant))) 
      (clojure.string/upper-case 
        (deaccent name)))
    true))

(defn- by-tags [restaurant tags]
  (if (not (empty? tags))
    (> (count (clojure.set/intersection (set (:tags restaurant)) (set tags))) 0)
    true))

(defn by-name-and-tags [restaurants name tags]
  (filter #(and (by-name % name) (by-tags % tags)) restaurants))