#!/bin/bash


docker stop bandit-nginx
docker stop bandit-rest

docker rm bandit-nginx -f
docker rm bandit-rest -f

docker rmi bandit-nginx 
docker rmi bandit-rest

# Build the project
mvn package

# build the images
docker build -t bandit-rest bandit-rest/.
docker build -t bandit-nginx bandit-frontend/.

# run the containers
docker run -d --name bandit-rest --link bandit-db:bandit-db bandit-rest
docker run --name bandit-nginx -p 8082:80 --link bandit-rest:bandit-rest -d bandit-nginx

