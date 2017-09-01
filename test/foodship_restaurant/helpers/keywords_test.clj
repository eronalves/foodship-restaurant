(ns foodship-restaurant.helpers.keywords-test
  (:require 
    [clojure.test :refer :all]
    [foodship-restaurant.helpers.keywords :as keywords]))

(def complex-keywords-map
  {:restaurant/id 1
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
                      :banking/digit 1}})

(def simple-keywords-map
  {:id 1
  :name "Mexicaníssimo"
  :tags ["mexican"]
  :preparation-time {:min 45
                     :max 60}
  :business-hours [{:day-of-week :monday
                    :opening "18:00" 
                    :closing "23:45"}
                   {:day-of-week :tuesday
                    :opening "18:00" 
                    :closing "23:45"}
                   {:day-of-week :wednesday
                    :opening "18:00" 
                    :closing "23:45"}
                   {:day-of-week :thursday
                    :opening "18:00" 
                    :closing "23:45"}
                   {:day-of-week :friday
                    :opening "18:00" 
                    :closing "23:45"}
                   {:day-of-week :saturday
                    :opening "12:00" 
                    :closing "23:45"}
                   {:day-of-week :sunday
                    :opening "12:00" 
                    :closing "23:45"}]
  :location {:addres "Rua Ribeirão Claro"
             :number 192
             :neighborhood "Vila Olimpia"
             :city 5270
             :state 26
             :country 76
             :zipcode "04549-050"}
  :menu [{:title "Tostitacos"
          :category :appetizer
          :description "Tortilla de milho crocante (taco shell) recheada com proteína de soja refogada com cubinhos de berinjela e abobrinha, servido com alface e com ou sem sour cream."
          :ingredients ["farinha de trigo", "carne", "alface"]
          :price 25.9
          :serves 1}
         {:title "Quesadilla de queijo"
          :category :principal
          :description "Duas tortillas de trigo recheadas com queijo mussarela. Acompanha uma porção pequena de nachos, guacamole e chillibeans."
          :ingredients ["farinha de trigo", "queijo"]
          :price 22.9
          :serves 1}
         {:title "Molcajete tradicional"
          :category :principal
          :description "Cubinhos de alcatra e ﬁlé de frango grelhados com cebola, pimentão e queijo granado. Tudo isto é servido em uma pedra vulcânica estupidamente quente. Acompanha seis tortillas de trigo, guacamole e frijoles refritos"
          :ingredients ["alcatra", "frangro", "cebola", "pimentão", "queijo granado"]
          :price 46.9
          :serves 3}]
  :banking {:agency "xxxx"
            :account "xxxx"
            :digit 1}})            

(deftest transform-keywords
  (testing "must be transform complex keywords to a more simple with last splitted with slash"
    (is (= simple-keywords-map (keywords/transform-keywords complex-keywords-map)))))