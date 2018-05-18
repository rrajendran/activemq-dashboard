#!/usr/bin/env bash
mvn clean install
docker build -t sqs-dashboard:latest .

docker-compose down
docker-compose up -d
