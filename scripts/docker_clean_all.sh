#!/bin/bash


docker stop bandit-nginx
docker stop bandit-db
docker stop bandit-rest

docker rm bandit-nginx -f
docker rm bandit-db -f
docker rm bandit-rest -f

docker rmi bandit-nginx 
docker rmi bandit-db 
docker rmi bandit-rest



docker rm policeoffice_bandit-nginx_1 -f
docker rm policeoffice_bandit-rest_1 -f
docker rm policeoffice_bandit-db_1 -f

docker rmi policeoffice_bandit-nginx
docker rmi policeoffice_bandit-db
docker rmi policeoffice_bandit-rest

