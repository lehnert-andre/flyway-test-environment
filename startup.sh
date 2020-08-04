#!/bin/bash

docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker volume rm flyway_data

docker volume create flyway_data

docker-compose down

docker-compose up --build # -d


