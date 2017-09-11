(ns foodship-restaurant.adapters.db-adapter-test
  (:require 
    [clojure.test :refer :all]
    [bond.james :as bond :refer [with-stub!]]
    [foodship-restaurant.component-fixture :refer [setup-custom-with-lifecycle component]]
    [foodship-restaurant.ports.db.memory_db :as memory-db]
    [foodship-restaurant.adapters.db-adapter :as db-adapter]))

(use-fixtures :once (setup-custom-with-lifecycle {:memory-db {}}))

(def restaurant
  {:name "Mexicaníssimo" :tags ["mexican"]})

(def restaurants-memory-db
  [{:restaurant/name "Mexicaníssimo" :restaurant/tags ["mexican"]}
   {:restaurant/name "Aoyama" :restaurant/tags ["japanese"]}
   {:restaurant/name "Sancho Pancho" :restaurant/tags ["spanish"]}
   {:restaurant/name "Aokitama" :restaurant/tags ["japanese"]}])

(def restaurants
  [{:name "Mexicaníssimo" :tags ["mexican"]}
   {:name "Aoyama" :tags ["japanese"]}
   {:name "Sancho Pancho" :tags ["spanish"]}
   {:name "Aokitama" :tags ["japanese"]}])

(deftest retrieve
  (testing "must return a restaurant with the id parameter"
    (with-stub! [[memory-db/get-restaurant (fn [component id] restaurant)]]
      (let [resultset (db-adapter/retrieve-restaurant component 1)]
        (is (= 1 (-> memory-db/get-restaurant bond/calls count)))
        (is (= restaurant resultset))))))

(deftest all
  (testing "must return a list of all restaurants"
    (with-stub! [[memory-db/all (fn [component] restaurants-memory-db)]]
      (let [resultset (db-adapter/all-restaurants component)]
        (is (= 1 (-> memory-db/all bond/calls count)))
        (is (= restaurants resultset))))))

(deftest create-restaurant!
  (testing "must create a restaurant calling memory db"
    (with-stub! [[memory-db/insert-restaurant! (fn [component restaurant])]]
      (let [resultset (db-adapter/create-restaurant! component restaurant)
            calls-insert-restaurant (bond/calls memory-db/insert-restaurant!)
            args-insert-restaurant (:args (get calls-insert-restaurant 0))]
        (is (= 1 (count calls-insert-restaurant)))
        (is (= {} (nth args-insert-restaurant 0)))
        (is (= restaurant (nth args-insert-restaurant 1)))))))

(deftest update-restaurant!
  (testing "must create a restaurant calling memory db"
    (with-stub! [[memory-db/insert-restaurant! (fn [component restaurant])]]
      (let [resultset (db-adapter/update-restaurant! component restaurant)
            calls-insert-restaurant (bond/calls memory-db/insert-restaurant!)
            args-insert-restaurant (:args (get calls-insert-restaurant 0))]
        (is (= 1 (count calls-insert-restaurant)))
        (is (= {} (nth args-insert-restaurant 0)))
        (is (= restaurant (nth args-insert-restaurant 1)))))))



