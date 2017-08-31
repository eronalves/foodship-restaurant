(ns foodship-restaurant.domain.controller-test
  (:require 
    [clojure.test :refer :all]
    [foodship-restaurant.component-fixture :refer [setup-custom-with-lifecycle component]]
    [foodship-restaurant.domain.controller :as controller]))

(use-fixtures :once (setup-custom-with-lifecycle {:db {}}))

; (deftest get-restaurant 
;   (testing "must be return the info of restaurant"
;     (is (= {:name "America"} (controller/get-restaurant component 1)))))

