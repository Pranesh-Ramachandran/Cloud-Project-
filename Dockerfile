# Step 1: Build the application
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# Make sure mvnw has execution permission on Linux
RUN chmod +x mvnw
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Step 2: Create lightweight runtime container
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Cloud Run passes the PORT environment variable dynamically (defaults to 8080)
ENV PORT=8080
EXPOSE 8080

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

