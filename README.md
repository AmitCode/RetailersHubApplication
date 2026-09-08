# 🛒 RetailersHub

**RetailersHub** is a scalable, modular e-commerce platform built using a **microservices architecture** with Java and Spring Boot.

The platform is designed to simulate a real-world retail ecosystem where customers can browse products, place orders, manage accounts, and receive notifications, while administrators and retailers can manage products, users, orders, and other business operations.

The project follows a distributed architecture with independently deployable services, RESTful APIs, centralized authentication, role-based authorization, asynchronous communication, and containerized infrastructure.

---

## 📌 Project Status

🚧 **Currently under active development**

The project is being developed incrementally, starting with core authentication, user management, and notification capabilities and expanding toward a complete retail microservices ecosystem.

---

# 🏗️ Architecture

RetailersHub follows a **microservices-based architecture**, where each business capability is implemented as an independent service.

```text
                         ┌──────────────────────┐
                         │       Client         │
                         │ Web / Mobile / API   │
                         └──────────┬───────────┘
                                    │
                                    ▼
                         ┌──────────────────────┐
                         │     API Gateway      │
                         └──────────┬───────────┘
                                    │
             ┌──────────────────────┼──────────────────────┐
             │                      │                      │
             ▼                      ▼                      ▼
    ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
    │ Authentication  │    │   User Service  │    │ Product Service │
    │    Service      │    │                 │    │                 │
    └────────┬────────┘    └────────┬────────┘    └────────┬────────┘
             │                      │                      │
             ▼                      ▼                      ▼
       ┌──────────┐           ┌──────────┐           ┌──────────┐
       │ Database │           │ Database │           │ Database │
       └──────────┘           └──────────┘           └──────────┘

                                    │
                                    ▼
                           ┌──────────────────┐
                           │   Message Broker │
                           │      Kafka       │
                           └────────┬─────────┘
                                    │
                       ┌────────────┴────────────┐
                       ▼                         ▼
              ┌─────────────────┐       ┌─────────────────┐
              │ Notification    │       │ Other Consumer  │
              │ Service         │       │ Services        │
              └─────────────────┘       └─────────────────┘
```

---

# 📦 Microservices

| Service                   | Responsibility                                                 | Status            |
| ------------------------- | -------------------------------------------------------------- | ----------------- |
| **AuthenticationService** | Authentication, login, registration, JWT, account verification | 🚧 In Development |
| **UserService**           | User management and user-related operations                    | 🚧 In Development |
| **NotificationService**   | Notifications and communication with users                     | 🚧 In Development |
| **ProductService**        | Product catalog and product management                         | 📋 Planned        |
| **RetailerService**       | Retailer/vendor management                                     | 📋 Planned        |
| **OrderService**          | Order creation, processing and lifecycle management            | 📋 Planned        |
| **PaymentService**        | Payment processing and transaction management                  | 📋 Planned        |
| **RolePermissionService** | Role-based access control and permission management            | 📋 Planned        |
| **ApiGateway**            | Centralized API routing and request handling                   | 📋 Planned        |
| **ServiceRegistry**       | Service discovery and registration                             | 📋 Planned        |

---

# 🔐 Authentication & Authorization

Authentication is handled independently from the core business services.

The authentication layer is responsible for:

* User registration
* User login
* JWT-based authentication
* Password management
* Account verification
* OTP-based verification
* Forgot-password workflow
* Secure API access
* Authentication-related validations

### Planned Authorization Model

RetailersHub will implement **Role-Based Access Control (RBAC)**.

Example roles:

```text
SUPER_ADMIN
ADMIN
RETAILER
PRODUCT_MANAGER
ORDER_MANAGER
CUSTOMER
```

Permissions will define what operations each role can perform.

Example:

```text
USER_CREATE
USER_READ
USER_UPDATE
USER_DELETE

PRODUCT_CREATE
PRODUCT_READ
PRODUCT_UPDATE
PRODUCT_DELETE

ORDER_CREATE
ORDER_READ
ORDER_UPDATE
ORDER_CANCEL
```

This allows authorization to be managed independently from authentication.

---

# 👥 Core User Types

The platform is designed around multiple real-world actors.

### Super Admin

Responsible for overall platform administration.

Typical responsibilities:

* Manage administrators
* Manage platform configuration
* Manage roles and permissions
* Monitor system activity
* Manage retailers

### Admin

Responsible for operational management.

Typical responsibilities:

* Manage users
* Manage products
* Manage retailers
* Manage orders
* View operational dashboards

### Retailer

Responsible for managing products and retail operations.

Typical responsibilities:

* Manage products
* Update inventory
* View orders
* Manage pricing
* Monitor sales

### Product Manager

Responsible for product catalog operations.

Typical responsibilities:

* Create products
* Update products
* Manage categories
* Manage inventory
* Update product information

### Order Manager

Responsible for order processing.

Typical responsibilities:

* View orders
* Process orders
* Update order status
* Handle cancellations
* Manage fulfillment workflow

### Customer

The end user of the retail platform.

Typical responsibilities:

* Register/login
* Browse products
* Search products
* Place orders
* Track orders
* Manage profile
* Receive notifications

---

# 🛠️ Technology Stack

## Backend

* **Java**
* **Spring Boot**
* **Spring Security**
* **Spring Cloud**
* **Spring Data JPA**
* **Hibernate**
* **REST APIs**
* **Maven**

## Security

* JWT
* Spring Security
* Role-Based Access Control
* Password hashing
* OTP verification

## Databases

* MySQL
* Oracle
* PostgreSQL

The project is designed to support different persistence technologies where required by individual services.

## Messaging

* Apache Kafka

Kafka will be used for asynchronous, event-driven communication between services.

Example:

```text
Order Created
     │
     ▼
 Kafka Topic
     │
     ├──────────────► Notification Service
     │
     ├──────────────► Payment Service
     │
     └──────────────► Inventory Service
```

## Caching

* Redis

Redis will be used for caching and other low-latency data access requirements.

## Infrastructure

* Docker
* Docker Compose
* Git
* GitHub

---

# 📁 Repository Structure

RetailersHub is maintained as a **monorepo**, allowing all microservices and supporting infrastructure to be managed from a single GitHub repository.

```text
RetailersHubApplication/
│
├── README.md
├── .gitignore
│
├── AuthenticationService/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       └── test/
│
├── UserService/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       └── test/
│
├── NotificationService/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       └── test/
│
├── ProductService/
│
├── RetailerService/
│
├── OrderService/
│
├── PaymentService/
│
├── RolePermissionService/
│
├── ApiGateway/
│
├── ServiceRegistry/
│
└── infrastructure/
    ├── docker/
    ├── kafka/
    ├── redis/
    └── database/
```

---

# 🔄 Communication Between Services

RetailersHub uses two communication patterns.

## Synchronous Communication

REST APIs are used when an immediate response is required.

Example:

```text
Client
  │
  ▼
API Gateway
  │
  ▼
User Service
  │
  ▼
Response
```

## Asynchronous Communication

Apache Kafka will be used for event-driven communication.

Example:

```text
Order Service
     │
     │ OrderCreated Event
     ▼
   Kafka
     │
     ├───────────────┐
     ▼               ▼
Notification     Payment
Service           Service
```

This reduces coupling between services and allows consumers to process events independently.

---

# 🔑 Authentication Flow

The authentication flow is designed around JWT-based security.

```text
                ┌──────────────┐
                │    Client    │
                └──────┬───────┘
                       │
                 Login Request
                       │
                       ▼
              ┌──────────────────┐
              │ Authentication   │
              │ Service          │
              └────────┬─────────┘
                       │
                 Validate User
                       │
                       ▼
                ┌──────────────┐
                │   Database   │
                └──────┬───────┘
                       │
                 User Valid
                       │
                       ▼
                Generate JWT
                       │
                       ▼
                    Client
```

Subsequent requests contain the JWT:

```text
Authorization: Bearer <JWT>
```

The token is validated before protected resources are accessed.

---

# 🔒 Security Principles

The project follows common backend security practices:

* Stateless authentication
* JWT-based authorization
* Password hashing
* Role-based access control
* Permission-based authorization
* Protected REST endpoints
* Environment-based configuration
* Secrets excluded from source control
* Input validation
* Exception handling
* Secure service-to-service communication

---

# 🗄️ Database Strategy

Each microservice is designed to own its data.

```text
AuthenticationService ──► Authentication DB

UserService ────────────► User DB

ProductService ─────────► Product DB

OrderService ───────────► Order DB

PaymentService ─────────► Payment DB
```

Services should communicate through APIs or events rather than directly accessing another service's database.

This maintains service boundaries and reduces coupling.

---

# 📡 API Design

The services expose RESTful APIs.

Example Authentication APIs:

```text
POST /auth/register
POST /auth/login
POST /auth/forgot-password-request
POST /auth/reset-password
```

Example User APIs:

```text
POST   /userService/createNewUser
GET    /userService/{id}
PUT    /userService/{id}
DELETE /userService/{id}
```

API documentation will be provided using OpenAPI/Swagger.

---

# 🐳 Docker

The services are intended to be containerized using Docker.

Example:

```text
                    Docker Host
                         │
       ┌─────────────────┼─────────────────┐
       │                 │                 │
       ▼                 ▼                 ▼
 Authentication      User Service    Notification
   Container           Container       Container
       │                 │                 │
       └─────────────────┼─────────────────┘
                         │
                  Infrastructure
                         │
             ┌───────────┼───────────┐
             ▼           ▼           ▼
          MySQL        Redis       Kafka
```

Docker Compose will be used to simplify local development and infrastructure setup.

---

# ⚙️ Local Development

## Prerequisites

Make sure the following are installed:

* Java
* Maven
* Git
* Docker
* Docker Compose
* MySQL / PostgreSQL depending on the service
* IntelliJ IDEA or another Java IDE

---

# 🚀 Running a Service

Navigate to the required service:

```bash
cd AuthenticationService
```

Build the project:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

The same approach can be used for the other services.

---

# 🐳 Running With Docker

Once Docker configuration is available:

```bash
docker compose up --build
```

To stop the containers:

```bash
docker compose down
```

---

# 🧪 Testing

The project uses automated tests to validate service functionality.

Testing includes:

* Unit testing
* Controller testing
* Service-layer testing
* Repository testing
* Integration testing
* Security testing

Run tests using:

```bash
mvn test
```

---

# 📚 API Documentation

API documentation will be available through Swagger/OpenAPI.

Typical endpoints:

```text
/swagger-ui/index.html
/v3/api-docs
```

---

# 📈 Planned Features

The platform will gradually include:

* [x] Authentication Service
* [x] User Service
* [x] Notification Service
* [ ] Role & Permission Management
* [ ] Product Service
* [ ] Retailer Service
* [ ] Order Service
* [ ] Payment Service
* [ ] API Gateway
* [ ] Service Discovery
* [ ] Kafka-based event processing
* [ ] Redis caching
* [ ] Docker Compose environment
* [ ] Centralized configuration
* [ ] Distributed tracing
* [ ] Monitoring and observability
* [ ] CI/CD pipeline
* [ ] Automated integration tests

---

# 🎯 Engineering Goals

The primary engineering goals of RetailersHub are:

### Scalability

Services can be independently scaled based on workload.

### Maintainability

Business capabilities are separated into independently maintainable services.

### Loose Coupling

Services communicate through well-defined REST APIs and asynchronous events.

### Security

Authentication and authorization are implemented using Spring Security and JWT.

### Resilience

The architecture is designed to isolate failures between services.

### Observability

Future iterations will introduce centralized logging, metrics, tracing, and monitoring.

---

# 📊 Future Observability Stack

The project is planned to integrate:

```text
Application Services
        │
        ├── Logs
        ├── Metrics
        └── Traces
              │
              ▼
       Observability Layer
              │
        ┌─────┼─────┐
        ▼     ▼     ▼
      Logs Metrics Traces
```

Potential technologies include:

* Spring Boot Actuator
* Micrometer
* Prometheus
* Grafana
* Distributed tracing

---

# 🔄 CI/CD

A CI/CD pipeline is planned using GitHub Actions.

The pipeline will eventually perform:

```text
Git Push
   │
   ▼
Build
   │
   ▼
Unit Tests
   │
   ▼
Integration Tests
   │
   ▼
Static Analysis
   │
   ▼
Docker Build
   │
   ▼
Deploy
```

---

# 🧠 Key Concepts Demonstrated

This project is intended to demonstrate practical knowledge of:

* Java
* Object-Oriented Programming
* Collections
* Exception Handling
* Multithreading
* Spring Boot
* Spring Security
* JWT
* REST APIs
* Microservices
* Spring Cloud
* API Gateway
* Service Discovery
* JPA/Hibernate
* Database Design
* SQL
* Kafka
* Redis
* Docker
* Distributed Systems
* Event-Driven Architecture
* Role-Based Access Control
* Unit Testing
* Integration Testing
* CI/CD

---

# 📌 Why a Monorepo?

RetailersHub uses a monorepo structure so that the complete application can be explored from a single repository.

```text
One Repository
      │
      ├── Authentication
      ├── Users
      ├── Products
      ├── Retailers
      ├── Orders
      ├── Payments
      └── Notifications
```

Each service remains independently structured and deployable while the entire system can be versioned and reviewed together.

This also makes the project easier to share as a portfolio project.

---

# 👨‍💻 Author

**Amit Kumar Pandey**

Backend Software Engineer

Primary interests:

* Java
* Spring Boot
* Microservices
* Distributed Systems
* Backend Architecture
* REST APIs
* Database Design

---

# ⭐ Project

If you find this project useful or interesting, consider giving the repository a ⭐.

**Repository:**
https://github.com/AmitCode/RetailersHubApplication
