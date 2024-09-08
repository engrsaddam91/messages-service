# Contax Assignment

Assignment: Message Service

### Prerequisite:

- Java JDK 17
- Docker (in the case you directly want to run application)
- Maven

### Assumptions:

- It is simple message service which is build according to requested requirements.
- It has crud operations to fetch, add, update, delete messages from the messages table
- API is Documented Using Swagger

### Docker

The application contain docker file and shell script as well so in the case if docker is running on your system. You
just need to run dockerrun.sh file and message service can be accessible on port 8080

### Technologies

- Java 17
- Maven
- Spring boot & Rest
- Spring JPA
- H2 Database
- Map Structs (Mapper)
- Springfox Swagger Documentation
- Integration & Unit Testing
- Junit5
- Mockito And MockMVC
- Lombok
- Builder Pattern
- Logging
- Docker

### Clean And Build

mvn clean install

### Build And Test

mvn clean test

### Swagger URL

please see the link for documentation of API http://localhost:8080/swagger-ui/#/message-controller

### Message Service URL

http://localhost:8080/messages

