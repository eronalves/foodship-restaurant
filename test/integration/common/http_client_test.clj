(ns integration.common.http-client-test
  (:require
    [clojure.test :refer :all]
    [org.httpkit.client :as http]
    [cheshire.core :refer :all]))

(defn- parse-body [body]
  (parse-string body true))

(defn- create-url [port url]
  (str "http://localhost:" port url))

(defn- create-url-account [port api-version url number]
  (str (create-url port (str "/api/" api-version "/account/" number)) url))

(defn- do-home [port]
  @(http/get (create-url port "") {:as :text}))

(defn- home [port]
  (testing "GET '/' endpoint must be return a message to access /api-docs"
    (let [{:keys [status headers body error] :as resp} (do-home port)]
      (is (= 200 status))
      (is (= "Welcome to Foodship Restaurant. Access the url /api-docs for further information about this API" body)))))

(defn- not-found [port]
  (testing "Random endpoint must be not found message"
    (let [{:keys [status headers body error] :as resp}
          @(http/get (create-url port "/batman-cave") {:as :text})]
      (is (= 404 status))
      (is (= "Not Found" body)))))


(defn run-integration [port api-version]
  (home port)
  (not-found port))

(defn check-if-is-not-on-port [port]
  (testing "GET '/' endpoint must be return an error response"
    (let [{:keys [status headers body error] :as resp} (do-home port)]
      (is (= nil status))
      (is (= java.net.ConnectException (type error)))
      (is (= "Connection refused" (.getMessage error))))))