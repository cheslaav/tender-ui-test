FROM markhobson/maven-chrome

COPY ./ /test-ui
WORKDIR /test-ui

CMD mvn clean test