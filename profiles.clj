{ :dev {:dependencies [[org.clojure/tools.namespace "0.2.11"]
                       [javax.servlet/servlet-api "2.5"]
                       [ring/ring-mock "0.3.0"]
                       [circleci/bond "0.3.0"]
                       [cheshire "5.7.1"]]
        :plugins [[com.jakemccrary/lein-test-refresh "0.21.1"]
                  [venantius/ultra "0.5.1"]]
        :source-paths ["dev"]
        :env {:http-port 3000}}

  :prod {:env {:http-port 8000
               :repl-port 8001}
          :dependencies [[org.clojure/tools.nrepl "0.2.12"]]}
                      
  :uberjar {:aot :all}

  :test-refresh {:notify-command ["terminal-notifier" "-title" "Tests" "-message"]}}