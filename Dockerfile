
FROM openjdk:17-slim

ARG JAR_FILE=target/*.jar

COPY /target/todoapp*.jar app.jar


ENTRYPOINT ["java", "-jar", "/app.jar"]
