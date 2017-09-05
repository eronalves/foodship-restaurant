(ns foodship-restaurant.ports.handler.routes.restaurant-test
  (:require
    [clojure.test :refer :all]
    [ring.mock.request :as mock]
    [bond.james :as bond :refer [with-stub!]]
    [cheshire.core :as cheshire]
    [foodship-restaurant.ports.handler.components :as components]
    [foodship-restaurant.domain.controller :as controller]))

(def app (components/make-handler {:domain-controller {} :deps [:domain-controller]}))
(def restaurant-route "/api/v1/restaurants")

(defn- parse-body [body]
  (cheshire/parse-string (slurp body) true))

(defn- mock-post [route body]
  (app 
    (-> (mock/request :post route)
        (mock/content-type "application/json")
        (mock/body  (cheshire/generate-string body)))))

(defn- mock-get [route]
  (app (-> (mock/request :get route))))

(def restaurants-coll
  [{:name "Mexicaníssimo" :tags ["mexican"]}
   {:name "Aoyama" :tags ["japanese"]}
   {:name "Sancho Pancho" :tags ["spanish"]}
   {:name "Aokitama" :tags ["japanese"]}])

(deftest restaurants
  (testing "GET '/restaurants' endpoint without params must return a list of restaurants"
    (with-stub! [[controller/restaurants (fn [component name tags] restaurants-coll)]]
      (let [response (mock-get restaurant-route)
            body     (parse-body (:body response))
            calls-restaurants (bond/calls controller/restaurants)
            args-restaurants (:args (get calls-restaurants 0))]
        (is (= 1 (count calls-restaurants)))
        (is (= {} (nth args-restaurants 0)))
        (is (nil? (nth args-restaurants 1)))
        (is (nil? (nth args-restaurants 2)))
        (is (= 200 (:status response)))
        (is (= restaurants-coll body))))))