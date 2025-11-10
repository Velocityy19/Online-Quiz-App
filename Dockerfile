# ==========================
# Stage 1 — Build the App
# ==========================
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ==========================
# Stage 2 — Run the App
# ==========================
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose default port (Render will map its dynamic port anyway)
EXPOSE 8080

# ✅ Important: forward Render's PORT environment variable into Spring Boot
ENV PORT=8080
ENV SPRING_DATASOURCE_URL=""
ENV SPRING_DATASOURCE_USERNAME=""
ENV SPRING_DATASOURCE_PASSWORD=""
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_SHOW_SQL=true

# ✅ Updated ENTRYPOINT to respect Render's PORT dynamically
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
