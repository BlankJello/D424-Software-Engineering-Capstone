# FROM ubuntu:latest
# LABEL authors="hunte"

# ENTRYPOINT ["top", "-b"]

# Use an official Maven image to build the application
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy pom.xml and download dependencies (this helps cache dependencies)
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy the rest of the project files
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use a lightweight JRE image to run the app
FROM eclipse-temurin:17-jre-alpine

# Set working directory for the runtime container
WORKDIR /app

# Copy the packaged jar from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (or use ENV PORT in your app)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
