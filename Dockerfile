FROM openjdk:8-jdk-alpine

ADD target/demo-server-*.jar app.jar

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]