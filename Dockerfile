FROM clojure:alpine

RUN addgroup -S app && adduser -S -g app app 

ENV HOME=/home/app
WORKDIR $HOME

ENV APP_NAME=foodship-restaurant

WORKDIR $HOME/$APP_NAME
COPY project.clj $HOME/$APP_NAME
COPY profiles.clj $HOME/$APP_NAME

RUN lein install
COPY . $HOME/$APP_NAME

RUN chown -R app:app $HOME/*
USER app

RUN lein uberjar