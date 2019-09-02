FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar fse-api.jar
ENTRYPOINT ["java","-jar","/fse-api.jar"]