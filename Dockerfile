FROM openjdk:17-alpine
EXPOSE 8081
ARG JAR_FILE=target/wide-spring-rest-api-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]