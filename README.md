## Foodship Restaurant [![CircleCI](https://circleci.com/gh/eronalves/foodship-restaurant.svg?style=svg&circle-token=a1f1236f88cd2ac35ea224293077016dcfa6e42a)](https://circleci.com/gh/eronalves/foodship-restaurant) [![codecov](https://codecov.io/gh/eronalves/foodship-restaurant/branch/master/graph/badge.svg)](https://codecov.io/gh/eronalves/foodship-restaurant)

Clojure application to provide an API with basic features to manage restaurants and menus.

## Summary

The application was write in Clojure using Stuart Sierra's [Component framework](https://github.com/stuartsierra/component) and a vision of [Ports and Adapters Architecture (Hexagonal)](http://alistair.cockburn.us/Hexagonal+architecture) from Alistair Cockburn.

The objective was separation of concerns and isolate the domain services (pure functions) from the rest of application that treats external dependencies (like memory database and handler http).

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Install

After clonning or downloading this repository, execute the following in terminal to install the project dependencies:

    lein install
    
## Development

For execute this project in development it's necessary to open REPL because is using the Component to create isolated systems. Open REPL and execute the following commands to run, reset and stop the application.

    lein repl
    
For run dev system:

    (go)
    
For refresh files inside REPL:

    (reset)
    
For stop the application:

    (stop)
    
The port used for run the application is configured on profiles.clj in section { :dev :env {:http-port 3000}}.
If you don't want to change the port for now,the port is configured with 3000 per default.

Obs: Sometimes, when running (reset), the namespace of some files are not found, to solve this, run **lein clean**.

Do you want to know wich api services is available before to see production configuration? See in *docs/API.md*.

## Production

For production releases it's necessary build a uberjar and run passing to args the port that HTTP-KIT embbed will use.

Build:

    lein uberjar

Run:

	java -jar target/balances-api-0.1.0-SNAPSHOT-standalone.jar 3000

## Docker

For complement the production workflow, there is a docker compose configured with a revese proxy using NGINX.

**ATTENTION:**
It's configured for production and the port used to up the application is 80. If you want to change, please, edit the docker-compose.yml with the desire port.

	docker-compose build
	docker-compose up
	
For MAC OS, the NGINX Proxy Virtual Host needs more configuration to works, when execute this command, the application will can be access through localhost. For other operational system like linux, there is no problems, I guess.

## Tests

There are three settings available to run the tests: **default**, **integration** and **all**.

### Unit tests (default)

	lein test

### Integration tests (integration)

	lein test :integration
	
### All (combine unit and integration tests)

	lein test :all

## License

The MIT License (MIT)

Copyright © 2017 Eron Rodrigues Alves