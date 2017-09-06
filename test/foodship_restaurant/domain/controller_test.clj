(ns foodship-restaurant.domain.controller-test
  (:require 
    [clojure.test :refer :all]
    [bond.james :as bond :refer [with-stub!]]
    [foodship-restaurant.component-fixture :refer [setup-custom-with-lifecycle component]]
    [foodship-restaurant.adapters.db-adapter :as db-adapter]
    [foodship-restaurant.domain.domain-functions.filter-restaurants :as filter-restaurants]
    [foodship-restaurant.domain.controller :as controller]))

(use-fixtures :once (setup-custom-with-lifecycle {:db {}}))

(def restaurant
  {:restaurant/name "Mexicaníssimo" :restaurant/tags ["mexican"]})

(def restaurants
  [{:restaurant/name "Mexicaníssimo" :restaurant/tags ["mexican"]}
   {:restaurant/name "Aoyama" :restaurant/tags ["japanese"]}
   {:restaurant/name "Sancho Pancho" :restaurant/tags ["spanish"]}
   {:restaurant/name "Aokitama" :restaurant/tags ["japanese"]}])

(def filtered-restaurants
  [{:restaurant/name "Mexicaníssimo" :restaurant/tags ["mexican"]}])

(deftest get-restaurant
  (testing "must be return the info of restaurant"
    (with-stub! [[db-adapter/retrieve-restaurant (fn [component id] restaurant)]]
      (let [resultset (controller/get-restaurant component 1)]
        (is (= 1 (-> db-adapter/retrieve-restaurant bond/calls count)))
        (is (= restaurant resultset)))))

  (testing "must be return nil when not found restaurant"
    (with-stub! [[db-adapter/retrieve-restaurant (fn [component id] nil)]]
      (let [resultset (controller/get-restaurant component 2)]
        (is (= 1 (-> db-adapter/retrieve-restaurant bond/calls count)))
        (is (= nil resultset))))))

(deftest restaurants
  (testing "must be return filtered list of restaurants"
    (with-stub! [[db-adapter/all-restaurants (fn [component] restaurants)]
                 [filter-restaurants/by-name-and-tags (fn [restaurants name tags] filtered-restaurants)]]
      (let [resultset (controller/restaurants component "mex" ["mexican"])
            calls-filter-restaurants (bond/calls filter-restaurants/by-name-and-tags)
            args-filter-restaurants (:args (get calls-filter-restaurants 0))]
        (is (= 1 (-> db-adapter/all-restaurants bond/calls count)))
        (is (= 1 (count calls-filter-restaurants)))
        (is (= restaurants (nth args-filter-restaurants 0)))
        (is (= "mex" (nth args-filter-restaurants 1)))
        (is (= ["mexican"] (nth args-filter-restaurants 2)))
        (is (= filtered-restaurants resultset))))))

