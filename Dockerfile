FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN ./gradlew build

COPY application/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]