FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn -pl order-api -am package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/order-api/target/order-api-1.0.0-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "order-api-1.0.0-SNAPSHOT.jar"]
