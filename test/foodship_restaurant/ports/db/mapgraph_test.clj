(ns foodship-restaurant.ports.db.mapgraph-test
  (:require 
    [clojure.test :refer :all]
    [foodship-restaurant.component-fixture :refer [setup-with-lifecycle component]]
    [foodship-restaurant.ports.db.mapgraph :as mapgraph]))

(use-fixtures :once (setup-with-lifecycle mapgraph/create {}))

(deftest initial-data-and-insert-and-all-function
  (testing "must be inserted inital date on create component and return on all function"
    (let [restaurants (mapgraph/all component)]
      (is (not (nil? restaurants)))
      (is (= 2 (count restaurants))))))
      
(deftest get-restaurant
  (testing "must be retrieve the restaurant by id"
    (let [restaurant (mapgraph/get-restaurant component 1)]
      (is (not (nil? restaurant)))
      (is (= 1 (:restaurant/id restaurant))))))