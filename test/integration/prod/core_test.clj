(ns integration.prod.core-test
  (:require
    [clojure.test :refer :all]
    [environ.core :refer [env]]
    [foodship-restaurant.core :refer [-main]]
    [integration.common.http-client-test :refer [run-integration]]))

(def port "3010")

(deftest ^:integration main-production
  (testing "must serve application in production mode and provide the APIs"
    (-main port)
    (run-integration port "v1")))

(deftest ^:integration main-production-without-port
  (testing "must return error when not pass port to function args"
    (is (thrown-with-msg?
          clojure.lang.ExceptionInfo
          #"Provide the http port on first argument when executing production enviorment"
          (-main)))))