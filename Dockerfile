FROM --platform=linux/amd64 maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

FROM --platform=linux/amd64 eclipse-temurin:17 AS runtime
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY application.properties application.properties
COPY keystore keystore
COPY truststore truststore

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:///app/application.properties"]