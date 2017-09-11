(ns foodship-restaurant.ports.handler.routes.route-schemas
  (:require [schema.core :as s]))

(s/defschema FilterData
  {(s/optional-key :name) s/Str
   (s/optional-key :tags) [s/Str]})

(s/defschema CreateAlterRestaurant
  {(s/optional-key :id) s/Int
   :name s/Str
   :tags [s/Str]
   :preparation-time {:min s/Int
                                 :max s/Int}
   :business-hours [{:day s/Keyword
                     :opening s/Str
                     :closing s/Str}]
   (s/optional-key :location) {:addres s/Str
                               :number s/Int
                               :neighborhood s/Str
                               :city s/Int
                               :state s/Int
                               :country s/Int
                               :zipcode s/Str}
   (s/optional-key :menu) [{:title s/Str
                            :category s/Str
                            :ingredients [s/Str]
                            :price s/Num
                            :serves s/Int}]
   (s/optional-key :banking) {:agency s/Int
                              :account s/Str
                              :digit s/Int}})