FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY ./target/ms-server-discovery-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT java -jar app.jar