
# 🛒 E-Commerce Microservices Application

This is a full-featured e-commerce system built using **Spring Boot 3**, **Spring Cloud**, and **Kafka**, following a microservices architecture. The project simulates a real-world e-commerce flow, including product management, order processing, payments, and user notifications, all coordinated via asynchronous communication and service orchestration.

## 🧩 System Architecture

The application is composed of several microservices, each responsible for a distinct business capability. All services are loosely coupled and communicate via Kafka topics when needed.

![Microservices Architecture Diagram](diagrams/micro-services-async-communication.drawio.png

### 📦 Microservices

- **Customer Service**: Manages customer data and handles authentication (integrated with Keycloak).
- **Product Service**: Manages the product catalog and inventory, connects to a relational database.
- **Order Service**: Handles order creation and coordination between other services.
- **Payment Service**: Processes payments after order confirmation.
- **Notification Service**: Sends confirmation notifications once payments are successful.

### 🌐 Infrastructure Services

- **API Gateway**: Built using Spring Cloud Gateway for routing external requests to the appropriate internal services.
- **Discovery Server (Eureka)**: Used for service registration and discovery.
- **Config Server**: Centralized configuration management for all microservices.
- **Kafka & Zookeeper**: Enables asynchronous communication via topics for better scalability and decoupling.
- **Zipkin**: Used for distributed tracing and debugging.
- **Keycloak**: Handles authentication and authorization using OAuth2.

## 🔄 Communication Flow

1. **Customer places an order** through the API Gateway.
2. **Order Service** validates and creates the order.
3. A **Kafka event** is published to notify the **Payment Service**.
4. Once the payment is processed, a **Kafka event** notifies the **Notification Service**.
5. Notification is sent to the customer.

## 🧱 Design Patterns and Practices

- Modular structure with separate packages for each bounded context.
- Event-Driven communication model with Kafka.
- Centralized error handling and logging.
- Dockerized environment setup for easy deployment and testing.
- Uses a monorepo structure to keep all microservices organized within a single workspace.

## 🔐 Security

- OAuth2-based security implemented using **Keycloak**.
- API Gateway handles token validation and role-based routing.

## 📊 Observability

- Tracing requests across services using **Zipkin**.
- Logs include correlation IDs to trace user flows across the system.

---

> Designed and built with ❤️ by Mohamed Amgad
