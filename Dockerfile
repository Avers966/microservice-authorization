FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=impl/target/microservice-authorization-impl-1.0.0-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","app.jar"]
EXPOSE 8080
