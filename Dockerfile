FROM openjdk:11-jre

COPY build/libs/Calculator-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
EXPOSE 8765

ENTRYPOINT ["java", "-jar", "app.jar"]