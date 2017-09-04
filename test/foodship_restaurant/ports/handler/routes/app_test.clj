(ns foodship-restaurant.ports.handler.routes.app-test
  (:require 
    [clojure.test :refer :all]
    [ring.mock.request :as mock]
    [foodship-restaurant.ports.handler.components :as components]))

(def app (components/make-handler {}))

(deftest home
  (testing "GET '/' endpoint must be return a message to access /api-docs"
      (let [response (app (mock/request :get "/"))]
        (is (= 200 (:status response)))
        (is (= "Welcome to Foodship Restaurant. Access the url /api-docs for further information about this API" 
                (:body response))))))
                
(deftest not-found
  (testing "Random endpoint must be not found message"
      (let [response (app (mock/request :get "/batman-cave"))]
        (is (= 404 (:status response)))
        (is (= "Not Found" (:body response))))))