# Java 17 runtime
FROM eclipse-temurin:17-jre

# Working directory
WORKDIR /app

# Copy the built JAR
COPY target/*.jar app.jar

# Spring Boot port
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]