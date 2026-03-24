FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY pixbuddy-v1.0.2.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]