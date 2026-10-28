# Use a lightweight Java 21 image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Make the Maven wrapper executable and build the app
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Run the jar file
ENTRYPOINT ["java", "-jar", "target/student-api-0.0.1-SNAPSHOT.jar"]