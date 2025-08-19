FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn -pl order-consumer -am package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/order-consumer/target/order-consumer-1.0.0-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "order-consumer-1.0.0-SNAPSHOT.jar"]
