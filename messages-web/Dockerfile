FROM openjdk:17-oracle
ADD target/messages-service.jar messages-service.jar
ENTRYPOINT ["java", "-jar", "messages-service.jar"]
EXPOSE 8080