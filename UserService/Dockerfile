FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp

COPY UserService/target/UserService-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]