# TeamC-API

Database
---
1. The api connects to database: AgileSprints_SamB

Config
---
1. Create an empty file called employeedb.properties in <directory_clone_was_ran>/TeamC-API/
2. The following environment variables need to be set to enable database connection:
- host
- user
- password
- db

How to start the java-dropwizard-swagger-jdbc application
The project requires Java 11
---
1. Run `mvn clean install -DskipTests=true` to build your application
2. Start application with `java -jar target/JavaWebService-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080/api/job-roles`

Swagger
---
To see your applications Swagger UI `http://localhost:8080/swagger`

Tests
---

1. Run `mvn clean test` to run unit tests
2. Run `mvn clean integration-test` to run integration tests (this may require VPN for database access)

NOTE: Integration tests require connection to the database which may require VPN
