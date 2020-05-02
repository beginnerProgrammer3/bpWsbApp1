FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
CMD java -jar mailwithrsssender-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app.jar"]