FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/*.jar /app/app.jar

ENV SERVER_PORT=8081

EXPOSE 8081 8082

CMD ["java", "-jar", "-Dserver.port=${SERVER_PORT}", "/app/app.jar"]
