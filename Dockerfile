# Stage 1: Build JAR file
FROM gradle:8.4.0-jdk17-jammy AS build
WORKDIR /app
COPY build.gradle .
COPY src ./src

# Set Arg Values
ARG WALLET_DB_HOST
ARG WALLET_DB_PORT
ARG WALLET_DB_NAME
ARG WALLET_DB_USER
ARG WALLET_DB_PASSWORD

# Set environment variables
ENV WALLET_DB_HOST=$WALLET_DB_HOST
ENV WALLET_DB_PORT=$WALLET_DB_PORT
ENV WALLET_DB_NAME=$WALLET_DB_NAME
ENV WALLET_DB_USER=$WALLET_DB_USER
ENV WALLET_DB_PASSWORD=$WALLET_DB_PASSWORD

RUN apt-get update && apt-get install -y gettext-base
RUN envsubst < /app/src/main/resources/application.properties > /app/application-substituted.properties

# Move the substituted properties file to replace the original
RUN mv /app/application-substituted.properties /app/src/main/resources/application.properties

RUN cat /app/src/main/resources/application.properties

# Use envsubst to replace variables in application.properties
COPY application.properties /app/application.properties

RUN gradle build

# Stage 2: Build Docker image
FROM eclipse-temurin:17-jdk-focal
VOLUME /tmp
COPY --from=build /app/build/libs/app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080
