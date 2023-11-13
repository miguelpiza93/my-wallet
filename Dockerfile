# Stage 1: Build JAR file
FROM gradle:8.4.0-jdk17-jammy AS build
WORKDIR /app
COPY build.gradle .
COPY src ./src
RUN gradle build

# Stage 2: Build Docker image
FROM eclipse-temurin:17-jdk-focal
VOLUME /tmp
COPY --from=build /app/build/libs/app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080
