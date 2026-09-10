FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/DockerDemoApp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

# java jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]