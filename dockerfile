from tomcat:latest

RUN apt update && apt upgrade -y
RUN mv /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/webapps/OLDROOT

COPY build/libs/gerecht-0.1.0.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080