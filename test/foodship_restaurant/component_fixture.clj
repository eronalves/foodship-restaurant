(ns foodship-restaurant.component-fixture
  (:require [com.stuartsierra.component :as component]))

(def ^:dynamic component nil)

(defn setup [fn-create deps]
  (fn [f]
    (binding [component (merge (fn-create) deps)]
      (f)
      (component/stop component))))

(defn setup-with-lifecycle [fn-create deps]
  (fn [f]
    (binding [component (merge (component/start (fn-create)) deps)]
      (f)
      (component/stop component))))

(defn setup-custom-with-lifecycle [custom-component]
  (fn [f]
    (binding [component (component/start custom-component)]
      (f)
      (component/stop component))))

