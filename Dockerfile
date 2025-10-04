# Step 1: Use an official JDK image as the base
FROM openjdk:17-jdk-slim

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy the jar file into the container
COPY target/hotel-service-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port your app runs on (default 8080)
EXPOSE 8080

# Step 5: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
