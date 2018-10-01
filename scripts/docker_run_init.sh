#!/bin/bash

cd ..


# Build the project
mvn package

# build the images
docker build -t todo-rest todo-rest/.
docker build -t todo-nginx todo-frontend/.

# run the containers
docker run -d --name todo-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=todo postgres:9.4.5
docker run -d --name todo-rest --link todo-db:todo-db todo-rest
docker run --name todo-nginx -p 8082:80 --link todo-rest:todo-rest -d todo-nginx

