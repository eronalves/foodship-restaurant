(ns foodship-restaurant.domain.domain-functions.helper-id)

(defn- parse-int [s]
  (Integer/parseInt (re-find #"\A-?\d+" s)))

(defn- generate-symbol [model]
  (.toString (gensym "restaurant")))

(defn generate-id [model]
  (parse-int
    (apply str (filter #(Character/isDigit %) (generate-symbol model)))))
