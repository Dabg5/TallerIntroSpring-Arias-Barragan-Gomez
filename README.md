# Music Discography Management

This web application manages music artists and their tracks. It was built with Spring Context, Jakarta Servlets, Maven, and an in-memory data store.

## Features

- List all registered artists.
- Create artists with a name and nationality.
- Search an artist by name and display all associated tracks.
- Delete an artist by ID.
- List all registered tracks with their associated artists.
- Create a track and associate it with one or more artists.
- Delete a track by ID.
- Load 10 artists and 50 tracks when the Spring context starts. Each initial artist has five tracks.

## Dependency Injection Configuration

This project uses Java configuration through `AppConfig`.

- `@Configuration` identifies the Spring configuration class.
- `@Bean` explicitly creates repositories, services, and the data initializer.
- Services receive their repositories through constructor injection.
- No XML configuration or component scanning is used.

## Requirements

- Java 17 or newer.
- Maven 3.9 or newer.
- Apache Tomcat 10 or newer.

Tomcat 10+ is required because the application uses `jakarta.servlet.*` packages.

## Build

Run the following command from the project root:

```bash
mvn clean package
```

Maven creates the deployable file at:

```text
target/music-discography.war
```

## Deploy on Tomcat

1. Build the WAR file with Maven.
2. Copy `target/music-discography.war` to Tomcat's `webapps` directory.
3. Start Tomcat.
4. Open `http://localhost:8080/music-discography/` in a browser.

## Application Routes

| Route | Description |
| --- | --- |
| `/artists` | Lists all artists. |
| `/artists/create` | Displays the artist creation form. |
| `/artists/search` | Displays the artist search form and its tracks. |
| `/artists/delete` | Displays the artist deletion form. |
| `/tracks` | Lists all tracks and their artists. |
| `/tracks/create` | Displays the track creation form. |
| `/tracks/delete` | Displays the track deletion form. |

## Test

Run the automated tests with:

```bash
mvn test
```

The tests verify the model relationship, the initial data set, the service behavior, and the Spring Java configuration.
