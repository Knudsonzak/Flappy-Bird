#!/bin/bash
cd api
mvn clean package -DskipTests
java -jar target/flappy-bird-api-1.0.0.jar
