(ns foodship-restaurant.domain.domain-functions.helper-id-test
  (:require
    [clojure.test :refer :all]
    [foodship-restaurant.domain.domain-functions.helper-id :as helper-id]))

(deftest generate-id
  (testing "must be return an id with model pattern"
    (let [id (helper-id/generate-id "restaurant")]
      (is (integer? id)))))