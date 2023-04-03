# Anagram

This project allows you to evaluate words if they are anagram to each other. It tries to be as scalable and as performant
as possible. The logic behind anagram is quite simple but the idea of this represents the architecture and the scalability 
behind.

## Architecture

The application is separated into three layers using the hexagonal architecture:

- Application layer: Handles the user input layer including validated rest controllers, crones and the REST objects for
  requests and response, and also converts the Domain layer result into Application Layer.
- Domain layer: contains the business logic, entities and data models for the app.
- Infrastructure layer: provides implementations for interfaces defined in the domain layer, such as interacting with a
  database, cache repositories.

## Features

- Evaluate Anagram in GET using path Variable and POST with Body Request
- Evaluate list of strings to find the best possible anagram among them and the results.
- Search for anagram(will look to our stored anagrams for the cases that might suit you)
- Insert an Anagram to the Database.

The logic of storing the words is seperated into 2 concepts:
- Stateless words, these words are put into a cache everytime a user does an evaluation and waits for the crones.
- Stateful words. these words are retrieved periodically from cache and then inserted into the database.

## Testing

The whole app is well-tested with a code coverage of over 90%. Tests are written for all the modules.

## How to run

To run it, you will need to have the following installed:

- Java 17 or later
- Kotlin
- Gradle
- Docker

### Long way

1. Clone the repository
2. Run the command `docker-compose up` to start the application and the database.
3. The application will be running on `http://localhost:9000/`

### Short way
The project for the first time will take a lot for gradle to be finish on a new project. So you can achieve a faster
time by commenting the "app" service on the docker-compose.yml and then run it through your ide or by using gradlew 
bootRun. 