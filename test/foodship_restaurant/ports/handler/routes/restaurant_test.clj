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
        (is (= restaurants-coll body))))))