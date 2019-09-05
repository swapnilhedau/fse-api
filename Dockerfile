FROM tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/fse-api.war /usr/local/tomcat/webapps/fse-api.war
CMD ["catalina.sh", "run"]