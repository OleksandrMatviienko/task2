FROM openjdk:17.0.2-jdk as build
WORKDIR /task2

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests

FROM openjdk:17.0.2-jdk
COPY --from=build /task2/target/spring-api-docker.jar spring-api-docker.jar
ENTRYPOINT ["java","-jar","spring-api-docker.jar"]