(ns foodship-restaurant.ports.handler.routes.restaurant-test
  (:require
    [clojure.test :refer :all]
    [bond.james :as bond :refer [with-stub!]]
    [foodship-restaurant.ports.handler.routes.routes-util-test :as util]
    [foodship-restaurant.ports.handler.components :as components]
    [foodship-restaurant.domain.controller :as controller]))

(def app (components/make-handler {:domain-controller {} :deps [:domain-controller]}))
(def restaurant-route "/api/v1/restaurants")

(def restaurants-coll
  [{:name "Mexicaníssimo" :tags ["mexican"]}
   {:name "Aoyama" :tags ["japanese"]}
   {:name "Sancho Pancho" :tags ["spanish"]}
   {:name "Aokitama" :tags ["japanese"]}])

(def filtered-restaurants-coll
  [{:name "Mexicaníssimo" :tags ["mexican"]}
   {:name "Aokitama" :tags ["japanese"]}])

(def retrieved-restaurant {:name "Mexicaníssimo" :tags ["mexican"]})

(deftest restaurants
  (testing "GET '/restaurants' endpoint without params must return a list of restaurants"
    (with-stub! [[controller/restaurants (fn [component name tags] restaurants-coll)]]
      (let [response (util/mock-get app restaurant-route)
            body     (util/parse-body (:body response))
            calls-restaurants (bond/calls controller/restaurants)
            args-restaurants (:args (get calls-restaurants 0))]
        (is (= 1 (count calls-restaurants)))
        (is (= {} (nth args-restaurants 0)))
        (is (nil? (nth args-restaurants 1)))
        (is (nil? (nth args-restaurants 2)))
        (is (= 200 (:status response)))
        (is (= restaurants-coll body)))))

  (testing "GET '/restaurants' endpoint with params must return a filtered list of restaurants"
    (with-stub! [[controller/restaurants (fn [component name tags] filtered-restaurants-coll)]]
      (let [response (util/mock-get app (str restaurant-route "?name=i&tags=mexican&tags=japanese"))
            body     (util/parse-body (:body response))
            calls-restaurants (bond/calls controller/restaurants)
            args-restaurants (:args (get calls-restaurants 0))]
        (is (= 1 (count calls-restaurants)))
        (is (= {} (nth args-restaurants 0)))
        (is (= "i" (nth args-restaurants 1)))
        (is (= ["mexican", "japanese"] (nth args-restaurants 2)))
        (is (= 200 (:status response)))
        (is (= filtered-restaurants-coll body))))))

(deftest restaurant
  (testing "GET '/restaurant/:id endpoint with id"
    (with-stub! [[controller/get-restaurant (fn [component id] retrieved-restaurant)]]
      (let [response (util/mock-get app (str restaurant-route "/12"))
            body (util/parse-body (:body response))
            calls-restaurant (bond/calls controller/get-restaurant)
            args-restaurant (:args (get calls-restaurant 0))]
        (is (= 1 (count calls-restaurant)))
        (is (= {} (nth args-restaurant 0)))
        (is (= 12 (nth args-restaurant 1)))
        (is (= 200 (:status response)))
        (is (= retrieved-restaurant body))))))