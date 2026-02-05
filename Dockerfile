# syntax=docker/dockerfile:1

# --- Build stage ---
# Use a full JDK to compile the project. Keep this aligned with Gradle toolchain (Java 24).
FROM eclipse-temurin:24-jdk AS build

WORKDIR /workspace

# Leverage Docker layer caching: copy build scripts first, then sources.
COPY gradlew build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle

# Pre-fetch dependencies (best effort). This speeds up iterative builds.
RUN chmod +x ./gradlew \
  && ./gradlew --no-daemon -q dependencies || true

# Now copy the source.
COPY src ./src

# Build a runnable Spring Boot jar.
RUN ./gradlew --no-daemon clean bootJar


# --- Runtime stage ---
# Use a slim JRE to keep the final image small.
FROM eclipse-temurin:24-jre

# Security: run as non-root.
RUN useradd -r -u 10001 -g root appuser

WORKDIR /app

# Copy the built jar from the build stage.
# This uses a wildcard to avoid hardcoding the version.
COPY --from=build /workspace/build/libs/*-SNAPSHOT.jar /app/app.jar

ENV JAVA_OPTS="" \
    PORT=8080 \
    SPRING_PROFILES_ACTIVE=default

# Spring Boot listens on server.port; bind to all interfaces for containers.
EXPOSE 8080

USER 10001

# Use sh -c so $JAVA_OPTS and $PORT expand.
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -Dserver.port=${PORT} -Dserver.address=0.0.0.0 -jar /app/app.jar"]
