(ns foodship-restaurant.domain.schemas
  (:require [schema.core :as s]))

(def Restaurant
  {:restaurant/id s/Int
   :restaurant/name s/Str
   :restaurant/tags [s/Keyword]
   :restaurant/preparation-time {:preparation-time/min s/Int
                                 :preparation-time/max s/Int}
   :restaurant/business-hours {:business-hours/day s/Str
                               :business-hours/opening s/Str 
                               :business-hours/closing s/Str}
   (s/optional-key :restaurant/location) {:location/addres s/Str
                         :location/number s/Int
                         :location/neighborhood s/Str
                         :location/city s/Int
                         :location/state s/Int
                         :location/country s/Int
                         :location/zipcode s/Str}
   (s/optional-key :restaurant/menu) [{:menu/title s/Str
                      :menu/category s/Str
                      :menu/ingredients [s/Str]
                      :menu/price s/Num
                      :menu/serves s/Int}]
   (s/optional-key :restaurant/banking) {:banking/agency s/Int
                        :banking/account s/Str
                        :banking/digit s/Int}})