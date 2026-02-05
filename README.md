# KotlinSpringDemo

Docker Compose runs the Spring Boot app and a Postgres database.

## Setup env file

Copy the sample environment file and adjust values as needed before starting the stack:
```sh
cp .env.example .env
```

## Quick start

```sh
docker compose up --build
```

Then open `http://localhost:8080/hello`.

## Configuration

Defaults are in `.env` and can be changed there or via environment variables:

- `APP_PORT`
- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USER`
- `DB_PASSWORD`
- `SPRING_PROFILES_ACTIVE`

## Local build

```sh
./gradlew test
```
