# Use an official OpenJDK runtime as a base image
FROM openjdk:17-oracle 

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file (make sure your JAR file is named "app.jar") to the container
COPY target/profiles-0.0.1-SNAPSHOT.jar ./

# Expose the port that your Spring app is running on (e.g., 8080)
EXPOSE 8099

# Command to run the Spring app when the container starts
CMD ["java", "-jar", "profiles-0.0.1-SNAPSHOT.jar"]
