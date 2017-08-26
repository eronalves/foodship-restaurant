(ns foodship-restaurant.ports.handler.http-server
  (:require
    [org.httpkit.server :refer [run-server]]
    [com.stuartsierra.component :as component]
    [foodship-restaurant.ports.handler.components :refer [make-handler]]))

(defrecord Http-server [opts domain-controller deps]
  component/Lifecycle

  (start [this]
    (assoc this :http-kit (run-server (make-handler this) opts)))

  (stop [{:keys [http-kit] :as this}]
    (when http-kit (http-kit))
    (assoc this :http-kit nil)))

(defn create-http-server
  [deps port]
  (map->Http-server {:opts {:port port} :deps deps}))