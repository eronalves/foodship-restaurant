(ns foodship-restaurant.ports.handler.routes.routes-util-test
  (:require
    [cheshire.core :as cheshire]
    [ring.mock.request :as mock]))

(defn parse-body [body]
  (cheshire/parse-string (slurp body) true))

(defn mock-post [app route body]
  (app
    (-> (mock/request :post route)
        (mock/content-type "application/json")
        (mock/body  (cheshire/generate-string body)))))

(defn mock-get [app route]
  (app (-> (mock/request :get route))))