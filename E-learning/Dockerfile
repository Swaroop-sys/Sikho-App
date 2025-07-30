# Start with a base image that has Java and Maven
FROM maven:3.9.6-eclipse-temurin-17 as build

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Build the app
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
