FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp

COPY MarketService/target/MarketService-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]