(ns integration.dev.user-test
  (:require
    [clojure.test :refer :all]
    [org.httpkit.client :as http]
    [environ.core :refer [env]]
    [user :as user]
    [integration.common.http-client-test :refer [run-integration check-if-is-not-on-port]]))

(deftest ^:integration go
  (testing "must run dev system and provide apis to port on env"
    (user/go)
    (run-integration (env :http-port) "v1")))

(deftest ^:integration stop
  (testing "must stop dev system"
    (user/stop)
    (check-if-is-not-on-port (env :http-port))))