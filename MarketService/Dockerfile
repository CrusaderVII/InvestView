FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp

COPY MarketService/target/MarketService-1.0-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]