FROM 32bit/ubuntu:16.04
FROM openjdk:latest
COPY ./target/backend-0.0.1-SNAPSHOT.jar backend-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080