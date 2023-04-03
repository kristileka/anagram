FROM openjdk:17-jdk-slim

WORKDIR /app

COPY  . .

RUN ./gradlew clean build  -x test --no-daemon

ENTRYPOINT ["java", "-jar", "/app/application/build/libs/application-0.0.1-SNAPSHOT.jar"]
EXPOSE 9000