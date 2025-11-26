Spring Boot Course — Sample REST API

Overview
- A small Spring Boot application that exposes a simple REST API for managing users.
- Demonstrates typical layering: controller → service → repository (Spring Data JPA) with an entity–model mapper.
- On user creation, the service calls the public World Time API to set the `createdOn` timestamp.

Tech Stack
- Language: Java (Spring Boot 3.5.x)
- Frameworks/Libraries:
  - Spring Boot Web
  - Spring Data JPA
  - MySQL JDBC driver
  - Java 11+ `HttpClient`
  - Gson
- Build tool: Gradle (wrapper included, distribution 9.1)
- Note: The build declares a dependency on Unirest; the current implementation uses Java `HttpClient` instead. TODO: remove or adopt Unirest for HTTP calls.

Project Entry Point
- Main application class: `udemy.experiments.spring.course.Main`
  - Starts the Spring Boot application and auto-configures components.

Requirements
- Java: 17+ (recommended for Spring Boot 3.x). TODO: Confirm exact required JDK version for this project.
- MySQL server running locally with a database named `test` and credentials matching `application.yaml`.
  - Default JDBC URL: `jdbc:mysql://localhost:3306/test`
  - Default username: `root`
  - Default password: `test`
- Internet access (the app calls World Time API).

Configuration
- File: `src/main/resources/application.yaml`
  - time API settings (mapped to `TimeApiConfig`):
    - `timeapi.timezone`: default `Etc/UCT`
    - `timeapi.url`: default `http://worldtimeapi.org/api/timezone`
  - Spring datasource:
    - `spring.datasource.url`: JDBC URL for MySQL
    - `spring.datasource.username`
    - `spring.datasource.password`

Environment variables (optional)
- Spring Boot supports overriding config via environment variables. Examples:
  - `SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/test`
  - `SPRING_DATASOURCE_USERNAME=root`
  - `SPRING_DATASOURCE_PASSWORD=test`
  - Custom time API (these map to `timeapi.*`):
    - `TIMEAPI_TIMEZONE=Etc/UCT`
    - `TIMEAPI_URL=http://worldtimeapi.org/api/timezone`

Setup
1. Ensure Java and MySQL are installed and running.
2. Create a MySQL database named `test` (or update `application.yaml` with your DB name and credentials).
3. Clone the repository.
4. Adjust `src/main/resources/application.yaml` as needed.

Run
- Using Gradle wrapper (recommended):
  - Start the app:
    - macOS/Linux: `./gradlew bootRun`
    - Windows: `gradlew.bat bootRun`
  - Build a runnable jar:
    - `./gradlew bootJar`
    - Run the jar (example name may vary): `java -jar build/libs/spring-boot-course-1.0-SNAPSHOT.jar`

Available Gradle Tasks (common)
- `./gradlew clean` — clean build outputs
- `./gradlew build` — compile, test, and assemble
- `./gradlew bootRun` — run the application
- `./gradlew bootJar` — build an executable Spring Boot jar
- `./gradlew test` — run tests (see Tests section below)

API Endpoints
Base path: `/user`

- Health check
  - `GET /user/health` → returns `OK`

- Users collection
  - `GET /user/getUsers` → returns all users

- Single user
  - `GET /user/getUser/{id}` → returns user by id (404 if not found)
  - `DELETE /user/deleteUser/{id}` → deletes user (404 if not found, 204 on success)

- Create user
  - `POST /user/createUser`
  - Request body JSON:
    ```json
    {
      "id": 1,
      "firstName": "Jane",
      "lastName": "Doe",
      "email": "jane@example.com",
      "role": "admin",
      "createdOn": null
    }
    ```
  - Response: `201 Created`

- Update user
  - `PATCH /user/updateUser`
  - Request body JSON: same shape as create. Must include an existing `id`.
  - Response: `202 Accepted` with updated user, or `404 Not Found` if ID doesn’t exist.

Database & Persistence
- JPA entity: `udemy.experiments.spring.course.data.UserEntity` mapped to table `users` with columns:
  - `id` (IDENTITY), `firstName`, `lastName`, `email`, `role`, `createdOn`
- Repository: `UserRepository extends CrudRepository<UserEntity, Integer>`
- Mapper: `UserEntityMapper` converts between API model `User` and JPA `UserEntity`.
- Migrations: none included.
  - TODO: Add schema DDL and/or integrate Flyway or Liquibase for automated migrations.

Time Service
- `TimeService` fetches current time from `timeapi.url + "/" + timeapi.timezone` and deserializes with Gson.
- The `UserService.addUser` sets `createdOn` using the external time API before persisting.

Tests
- Dependencies for JUnit 5 are present in `build.gradle`, but no test classes are included yet.
- Run (will pass with zero tests): `./gradlew test`
- TODO: Add unit tests for controller, service, and mapper layers.

Project Structure
```
.
├── LICENSE
├── build.gradle
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── src/
    └── main/
        ├── java/udemy/experiments/spring/course/
        │   ├── Main.java
        │   ├── config/
        │   │   └── TimeApiConfig.java
        │   ├── controllers/
        │   │   └── UserController.java
        │   ├── data/
        │   │   └── UserEntity.java
        │   ├── models/
        │   │   ├── TimeResponse.java
        │   │   └── User.java
        │   ├── repositories/
        │   │   └── UserRepository.java
        │   ├── services/
        │   │   ├── ITimeService.java
        │   │   ├── IUserService.java
        │   │   ├── TimeService.java
        │   │   └── UserService.java
        │   └── utils/
        │       ├── IEntityMapper.java
        │       └── UserEntityMapper.java
        └── resources/
            └── application.yaml
```

Known Limitations / TODOs
- Confirm and document the exact required Java version.
- Add database schema DDL or migrations and align entity/table naming consistently.
- Add tests (unit/integration) and CI workflow.
- Consider Docker Compose for local MySQL and app runtime.
- Validate and possibly refactor the main method signature and visibility in `Main.java`. TODO: verify.

License
- This project is licensed under The Unlicense. See the `LICENSE` file for details.
