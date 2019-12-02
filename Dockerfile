FROM cheslaav/maven-jdk11-chrome:latest

COPY ./ /project
WORKDIR /project

CMD mvn clean install
