### fse-api

mvn clean build


#Dockerfile jar

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} fse-api.jar
ENTRYPOINT ["java","-jar","/fse-api.jar"]

# Build local docker image
$ docker build --build-args=target/*.jar -t fse-api:latest .


#Dockerfile war
$ docker build -t fse-api:0.0.1-SNAPSHOT .


# Run docker image
$ docker run -it -p 8080:8080 swapnilhedau/fse-api:0.0.1-SNAPSHOT

# Retag local image for docker hub
docker tag fse-api:0.0.1-SNAPSHOT swapnilhedau/fse-api:0.0.1-SNAPSHOT

# Push to docker hub repository
docker push swapnilhedau/fse-api:0.0.1-SNAPSHOT

# Using MySql command line client
login to command prompt
> create database fse;
> use fse;

# Use Create.sql file to create tables