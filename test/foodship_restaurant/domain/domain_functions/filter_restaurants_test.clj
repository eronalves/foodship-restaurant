(ns foodship-restaurant.domain.domain-functions.filter-restaurants-test
  (:require 
    [clojure.test :refer :all]
    [foodship-restaurant.domain.domain-functions.filter-restaurants :as filter]))

(def restaurants
  [{:restaurant/name "Mexicaníssimo" :restaurant/tags ["mexican"]}
   {:restaurant/name "Aoyama" :restaurant/tags ["japanese"]}
   {:restaurant/name "Sancho Pancho" :restaurant/tags ["spanish"]}
   {:restaurant/name "Aokitama" :restaurant/tags ["japanese"]}])

(deftest by-name-and-tags-only-name
  (testing "must be filter by name"
    (let [filteredData (filter/by-name-and-tags restaurants "Aoyama" nil)]
      (is (not (nil? filteredData)))
      (is (= (count filteredData) 1))
      (is (= "Aoyama" (:restaurant/name (nth filteredData 0))))))

  (testing "must be filter by name with accent"
    (let [filteredData (filter/by-name-and-tags restaurants "mexicanissimo" nil)]
      (is (not (nil? filteredData)))
      (is (= (count filteredData) 1))
      (is (= "Mexicaníssimo" (:restaurant/name (nth filteredData 0))))))

  (testing "must be filter by partial name without case"
    (let [filteredData (filter/by-name-and-tags restaurants "ao" nil)]
      (is (not (nil? filteredData)))
      (is (= (count filteredData) 2))
      (is (= "Aoyama" (:restaurant/name (nth filteredData 0))))
      (is (= "Aokitama" (:restaurant/name (nth filteredData 1))))))
  
  (testing "must be filter without match and return empty collection"
    (let [filteredData (filter/by-name-and-tags restaurants "Mc Donalds" nil)]
      (is (not (nil? filteredData)))
      (is (= (count filteredData) 0)))))

(deftest by-name-and-tags-only-tags
  (testing "must be filter by tags"
    (let [filteredData (filter/by-name-and-tags restaurants nil ["mexican" "japanese"])]
      (is (not (nil? filteredData)))
      (is (= (count filteredData) 3))
      (is (= "Mexicaníssimo" (:restaurant/name (nth filteredData 0))))
      (is (= "Aoyama" (:restaurant/name (nth filteredData 1))))
      (is (= "Aokitama" (:restaurant/name (nth filteredData 2))))))

  (testing "must be filter without match and return empty collection"
    (let [filteredData (filter/by-name-and-tags restaurants nil ["indian"])]
      (is (not (nil? filteredData)))
      (is (= (count filteredData) 0)))))

(deftest by-name-and-tags
  (testing "must be filter by name and tags"
    (let [filteredData (filter/by-name-and-tags restaurants "mex" ["mexican", "japanese"])]
      (is (not (nil? filteredData)))
      (is (= (count filteredData) 1))
      (is (= "Mexicaníssimo" (:restaurant/name (nth filteredData 0))))))
      
  (testing "must be filter without match and return empty collection"
    (let [filteredData (filter/by-name-and-tags restaurants "Maoe" ["indian"])]
      (is (not (nil? filteredData)))
      (is (= (count filteredData) 0)))))
  