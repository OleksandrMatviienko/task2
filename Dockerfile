FROM openjdk:17.0.2-jdk
ADD target/spring-api-docker.jar spring-api-docker.jar
ENTRYPOINT ["java","-jar","spring-api-docker.jar"]