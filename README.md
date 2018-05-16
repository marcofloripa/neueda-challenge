# ShortenedUrl API
Api responsible for generate shortened urls

### Prerequisites

Postgresql

* username: postgres
* password: postgres
* database: neueda

MongoDB

* database: analytics

Or change the file application.properties located in src/main/resources

### Data Definition Language (DDL)

The project use [Liquibase](https://www.liquibase.org/index.html) for generate the database structure.

The files are located in src/main/resources/db

### Running the app

```
mvn spring-boot:run
```
### Swagger

```
http://localhost:8080/swagger-ui.html
```

### TODO
* Docker
* More unit tests
