(ns foodship-restaurant.ports.handler.routes.app-test
  (:require 
    [clojure.test :refer :all]
    [ring.mock.request :as mock]
    [bond.james :as bond :refer [with-stub!]]
    [ring.util.http-response :refer [ok]]
    [foodship-restaurant.ports.handler.routes.routes-util-test :as util]
    [foodship-restaurant.ports.handler.components :as components]))

(def app (components/make-handler {}))

(deftest home
  (testing "GET '/' endpoint must be return a message to access /api-docs"
      (let [response (util/mock-get app "/")]
        (is (= 200 (:status response)))
        (is (= "Welcome to Foodship Restaurant. Access the url /api-docs for further information about this API" 
                (:body response))))))
                
(deftest not-found
  (testing "random endpoint must be not found message"
      (let [response (util/mock-get app "/batman-cave")]
        (is (= 404 (:status response)))
        (is (= "Not Found" (:body response))))))

(deftest route-throwing-exception
  (testing "must be return internal server error with status 500 and message error"
    (with-stub! [[ok (fn [text] (throw (ex-info "Error to respond http request" {})))]]
      (let [response (util/mock-get app "/")
            body (util/parse-body (:body response))]
        (is (= 500 (:status response)))
        (is (= {:error {:message "Error to respond http request"}} body))))))