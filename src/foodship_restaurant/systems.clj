(ns foodship-restaurant.systems
  (:require [com.stuartsierra.component :as component]
            [foodship-restaurant.ports.handler.http-server :refer [create-http-server]]
            [foodship-restaurant.ports.db.mapgraph :as mapgraph]))

(defn default-system [http-port]
  (-> (component/system-map 
    ;Structural components to separate ports and domain.
    :db (component/using 
          {}
          [:memory-db])
 
    :domain-controller (component/using 
                         {}
                         [:db])
     
     ; Ports
     :memory-db (mapgraph/create)
 
     :http-server (create-http-server [:domain-controller] http-port))
 
     (component/system-using
       {:http-server [:domain-controller]})))      

(defn dev-system [http-port]
  (default-system http-port))


(defn prod-system [http-port]
  (default-system http-port))
