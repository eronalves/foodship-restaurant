(ns foodship-restaurant.domain.controller-test
  (:require
    [clojure.test :refer :all]
    [bond.james :as bond :refer [with-stub!]]
    [foodship-restaurant.component-fixture :refer [setup-custom-with-lifecycle component]]
    [foodship-restaurant.adapters.db-adapter :as db-adapter]
    [foodship-restaurant.domain.domain-functions.filter-restaurants :as filter-restaurants]
    [foodship-restaurant.domain.controller :as controller]
    [foodship-restaurant.domain.domain-functions.helper-id :as helper-id]
    [foodship-restaurant.adapters.db-adapter :as db]))

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

(def restaurant-to-create {:name "Burger King"})
(def created-restaurant {:id 323 :name "Burger King"})

(deftest create-restaurant
  (testing "must be create a restaurant"
    (with-stub! [[helper-id/generate-id (fn [model] 323)]
                 [db/create-restaurant! (fn [component restaurant] )]
                 [db/retrieve-restaurant (fn [component id] created-restaurant)]]
      (let [resultset (controller/create-restaurant! component restaurant-to-create)
            calls-generate-id (bond/calls helper-id/generate-id)
            args-generate-id (:args (get calls-generate-id 0))
            calls-create-restaurant! (bond/calls db/create-restaurant!)
            args-create-restaurant (:args (get calls-create-restaurant! 0))
            calls-retrieve-restaurant (bond/calls db/retrieve-restaurant)
            args-retrieve-restaurant (:args (get calls-retrieve-restaurant 0))]
        (is (= 1 (count calls-generate-id)))
        (is (= "restaurant" (nth args-generate-id 0)))
        (is (= 1 (count calls-create-restaurant!)))
        (is (= {} (nth args-create-restaurant 0)))
        (is (= created-restaurant (nth args-create-restaurant 1)))
        (is (= 1 (count calls-retrieve-restaurant)))
        (is (= {} (nth args-retrieve-restaurant 0)))
        (is (= 323 (nth args-retrieve-restaurant 1)))
        (is (= created-restaurant resultset))))))

(def restaurant-to-update {:id 323 :name "Burger King" :tags ["fast food"]})
(def restaurant-in-db {:id 323 :name "Burger King" })

(deftest update-restaurant
  (testing "must be update a restaurant"
    (with-stub! [[db/retrieve-restaurant (fn [component id] restaurant-in-db)]
                 [db/update-restaurant! (fn [component restaurant])]]
      (let [resultset (controller/update-restaurant! component 323 restaurant-to-update)
            calls-db-retrieve (bond/calls db/retrieve-restaurant)
            calls-db-update (bond/calls db/update-restaurant!)
            args-db-update (:args (get calls-db-update 0))]
        (is (= 1 (count calls-db-retrieve)))
        (is (= 1 (count calls-db-update)))
        (is (= {} (nth args-db-update 0)))
        (is (= restaurant-to-update (nth args-db-update 1)))
        (is (= restaurant-to-update resultset))))))

