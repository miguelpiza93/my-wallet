FROM eclipse-temurin:17-jdk-focal
VOLUME /tmp
COPY build/libs/Wallet-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080