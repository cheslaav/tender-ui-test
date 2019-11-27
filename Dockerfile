FROM qateamdocker/maven-java8-chrome:latest

COPY ./ /project
WORKDIR /project

CMD mvn clean install