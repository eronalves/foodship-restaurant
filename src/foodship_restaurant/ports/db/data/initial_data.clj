(ns foodship-restaurant.ports.db.data.initial-data
  (:require [foodship-restaurant.domain.schemas :as schemas]))

  ; http://www.geonames.org/childrenJSON?geonameId=3469034

(defn get-data [] 
  [{:restaurant/id 1
    :restaurant/name "Mexicaníssimo"
    :restaurant/tags ["mexican"]
    :restaurant/preparation-time {:preparation-time/min 45
                                  :preparation-time/max 60}
    :restaurant/business-hours [{:business-hours/day-of-week :monday
                                 :business-hours/opening "18:00" 
                                 :business-hours/closing "23:45"}
                                {:business-hours/day-of-week :tuesday
                                 :business-hours/opening "18:00" 
                                 :business-hours/closing "23:45"}
                                {:business-hours/day-of-week :wednesday
                                 :business-hours/opening "18:00" 
                                 :business-hours/closing "23:45"}
                                {:business-hours/day-of-week :thursday
                                 :business-hours/opening "18:00" 
                                 :business-hours/closing "23:45"}
                                {:business-hours/day-of-week :friday
                                 :business-hours/opening "18:00" 
                                 :business-hours/closing "23:45"}
                                {:business-hours/day-of-week :saturday
                                 :business-hours/opening "12:00" 
                                 :business-hours/closing "23:45"}
                                {:business-hours/day-of-week :sunday
                                 :business-hours/opening "12:00" 
                                 :business-hours/closing "23:45"}]
    :restaurant/location {:location/addres "Rua Ribeirão Claro"
                          :location/number 192
                          :location/neighborhood "Vila Olimpia"
                          :location/city 5270
                          :location/state 26
                          :location/country 76
                          :location/zipcode "04549-050"}
    :restaurant/menu [{:menu/title "Tostitacos"
                       :menu/category :appetizer
                       :menu/description "Tortilla de milho crocante (taco shell) recheada com proteína de soja refogada com cubinhos de berinjela e abobrinha, servido com alface e com ou sem sour cream."
                       :menu/ingredients ["farinha de trigo", "carne", "alface"]
                       :menu/price 25.9
                       :menu/serves 1}
                      {:menu/title "Quesadilla de queijo"
                       :menu/category :principal
                       :menu/description "Duas tortillas de trigo recheadas com queijo mussarela. Acompanha uma porção pequena de nachos, guacamole e chillibeans."
                       :menu/ingredients ["farinha de trigo", "queijo"]
                       :menu/price 22.9
                       :menu/serves 1}
                      {:menu/title "Molcajete tradicional"
                       :menu/category :principal
                       :menu/description "Cubinhos de alcatra e ﬁlé de frango grelhados com cebola, pimentão e queijo granado. Tudo isto é servido em uma pedra vulcânica estupidamente quente. Acompanha seis tortillas de trigo, guacamole e frijoles refritos"
                       :menu/ingredients ["alcatra", "frangro", "cebola", "pimentão", "queijo granado"]
                       :menu/price 46.9
                       :menu/serves 3}]
    :restaurant/banking {:banking/agency "xxxx"
                         :banking/account "xxxx"
                         :banking/digit 1}}

   {:restaurant/id 2
    :restaurant/name "Aoyama"
    :restaurant/tags ["japanese"]
    :restaurant/preparation-time {:preparation-time/min 45
                                  :preparation-time/max 60}
    :restaurant/business-hours [{:business-hours/day-of-week :monday
                                 :business-hours/opening "12:00" 
                                 :business-hours/closing "20:00"}
                                {:business-hours/day-of-week :tuesday
                                 :business-hours/opening "12:00" 
                                 :business-hours/closing "20:00"}
                                {:business-hours/day-of-week :wednesday
                                 :business-hours/opening "12:00" 
                                 :business-hours/closing "20:00"}
                                {:business-hours/day-of-week :thursday
                                 :business-hours/opening "12:00" 
                                 :business-hours/closing "20:00"}
                                {:business-hours/day-of-week :friday
                                 :business-hours/opening "12:00" 
                                 :business-hours/closing "20:00"}
                                {:business-hours/day-of-week :saturday
                                 :business-hours/opening "12:00" 
                                 :business-hours/closing "23:00"}
                                {:business-hours/day-of-week :sunday
                                 :business-hours/opening "12:00" 
                                 :business-hours/closing "23:00"}]
    :restaurant/location {:location/addres "Al. dos Arapanés"
                          :location/number 532
                          :location/neighborhood "Moema"
                          :location/city 5270
                          :location/state 26
                          :location/country 76
                          :location/zipcode "04524-001"}
    :restaurant/menu [{:menu/title "Tartar de Salmão"
                       :menu/category :appetizer
                       :menu/description "Salmão batido com molho especial Aoyama."
                       :menu/ingredients ["salmão", "molho"]
                       :menu/price 39.9
                       :menu/serves 2}
                      {:menu/title "Baterá Aoyama"
                       :menu/category :principal
                       :menu/description "Salmão, ovas, crispy de batata doce e gergelim."
                       :menu/ingredients ["salmão", "ovas", "crispy de batata doce", "gergelim"]
                       :menu/price 50.39
                       :menu/serves 2}
                      {:menu/title "Tempurá de Sorvete"
                       :menu/category :dessert
                       :menu/description "com caldas de morango, chocolate e maracujá"
                       :menu/ingredients ["calda de morango", "chocolate", "maracujá"]
                       :menu/price 39.9
                       :menu/serves 3}]
    :restaurant/banking {:banking/agency "xxxx"
                        :banking/account "xxxx"
                        :banking/digit 1}}])

(defn setup [component fn-insert]
  (run! #(fn-insert component %) (get-data)))
