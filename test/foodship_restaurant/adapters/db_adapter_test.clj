(ns foodship-restaurant.adapters.db-adapter-test
  (:require 
    [clojure.test :refer :all]
    [bond.james :as bond :refer [with-stub!]]
    [foodship-restaurant.component-fixture :refer [setup-custom-with-lifecycle component]]
    [foodship-restaurant.ports.db.mapgraph :as mapgraph]
    [foodship-restaurant.adapters.db-adapter :as db-adapter]))

(use-fixtures :once (setup-custom-with-lifecycle {:memory-db {}}))

(def restaurant
  {:restaurant/name "Mexicaníssimo" :restaurant/tags ["mexican"]})

(def restaurants
  [{:restaurant/name "Mexicaníssimo" :restaurant/tags ["mexican"]}
   {:restaurant/name "Aoyama" :restaurant/tags ["japanese"]}
   {:restaurant/name "Sancho Pancho" :restaurant/tags ["spanish"]}
   {:restaurant/name "Aokitama" :restaurant/tags ["japanese"]}])

(deftest retrieve
  (testing "must return a restaurant with the id parameter"
    (with-stub! [[mapgraph/get-restaurant (fn [component id] restaurant)]]
      (let [resultset (db-adapter/retrieve component 1)]
        (is (= 1 (-> mapgraph/get-restaurant bond/calls count)))
        (is (= restaurant resultset))))))

(deftest all
  (testing "must return a list of all restaurants"
    (with-stub! [[mapgraph/all (fn [component] restaurants)]]
      (let [resultset (db-adapter/all component)]
        (is (= 1 (-> mapgraph/all bond/calls count)))
        (is (= restaurants resultset))))))