# Builder
FROM maven:3.8.6-eclipse-temurin-17-alpine AS builder
WORKDIR /usr/src
COPY ./ /usr/src
ENV PORT=8080
RUN mvn clean package

# Server
FROM openjdk:17
COPY --from=builder /usr/src/target/desafio-backend.jar /desafio-backend.jar
WORKDIR /
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java" ,"-jar" ,"/desafio-backend.jar"]