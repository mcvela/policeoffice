#!/bin/bash

# Clean the project
mvn clean

# Clean all dockers
./scripts/docker_clean_all.sh

# Run all
./scripts/docker_start.sh