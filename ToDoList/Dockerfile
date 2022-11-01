FROM eclipse-temurin:17-jdk-alpine
MAINTAINER jameshskoh
COPY target/ToDoList-0.0.1-SNAPSHOT.jar ToDoList-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/ToDoList-0.0.1-SNAPSHOT.jar", "--spring.config.location=file:/container/config/todolist-application.yml"]
