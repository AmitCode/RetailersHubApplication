# 🛒 RetailersHub

**RetailersHub** is a scalable, modular e-commerce platform built using **Java, Spring Boot, and Microservices architecture**.

The project is being developed as a real-world backend system covering authentication, user management, product management, notifications, centralized configuration, API routing, containerization, and service-to-service communication.

The architecture is designed around independently deployable services, RESTful APIs, centralized configuration, JWT-based security, containerized deployment, and clear service boundaries.

---

## 📌 Project Status

🚧 **Actively under development**

### Current development focus

- Authentication and JWT-based security
- User management
- Product service
- Notification service
- Spring Cloud Config Server / Config Client
- Centralized configuration using Git-backed configuration
- Spring Cloud API Gateway
- Docker containerization
- Docker Compose for multi-service local environments
- Google Jib for container image creation
- Service-to-service communication using REST/WebClient

### Future development

- Service discovery
- Kafka-based event-driven communication
- Redis caching
- Distributed tracing
- Monitoring and observability
- CI/CD automation
- Additional business services such as orders, payments, retailer management, and role/permission management

---

# 🏗️ Architecture

RetailersHub follows a **microservices-based architecture** in which individual business capabilities are separated into independently deployable services.

The current architecture is centered around an API Gateway, centralized configuration, authentication, user/product/notification services, and independently managed databases.

```text
                              ┌──────────────────────┐
                              │       Client         │
                              │ Web / Mobile / API   │
                              └──────────┬───────────┘
                                         │
                                         ▼
                              ┌──────────────────────┐
                              │     API Gateway      │
                              │  Spring Cloud GW     │
                              └──────────┬───────────┘
                                         │
                ┌────────────────────────┼────────────────────────┐
                │                        │                        │
                ▼                        ▼                        ▼
       ┌─────────────────┐      ┌─────────────────┐      ┌─────────────────┐
       │ Authentication  │      │   User Service  │      │ Product Service │
       │    Service      │      │                 │      │                 │
       └────────┬────────┘      └────────┬────────┘      └────────┬────────┘
                │                        │                        │
                ▼                        ▼                        ▼
       ┌─────────────────┐      ┌─────────────────┐      ┌─────────────────┐
       │ Auth Database   │      │  User Database  │      │ Product Database│
       └─────────────────┘      └─────────────────┘      └─────────────────┘

                │
                │ REST / WebClient
                ▼
       ┌─────────────────────┐
       │ Notification Service│
       └──────────┬──────────┘
                  │
                  ▼
             Email / OTP

                  ▲
                  │
       ┌──────────┴──────────┐
       │   Config Client     │
       │ Authentication/User │
       │ Product/Notification│
       └──────────▲──────────┘
                  │
                  │ Centralized Configuration
                  │
       ┌──────────┴──────────┐
       │   Config Server     │
       │ Spring Cloud Config │
       └──────────┬──────────┘
                  │
                  ▼
             Git Repository
       Centralized Config Files
```

> **Note:** Kafka, Redis, service discovery, and additional business services are part of the target architecture but are not represented as currently implemented components unless explicitly listed in the project status below.

---

# 📦 Microservices & Infrastructure Components

| Component | Responsibility | Status |
|---|---|---|
| **AuthenticationService** | Registration, login, JWT authentication and authentication workflows | ✅ Implemented / Active Development |
| **UserService** | User creation and user-related operations | ✅ Implemented / Active Development |
| **ProductService** | Product catalog and product management | 🚧 Active Development |
| **NotificationService** | Email/notification-related operations | 🚧 Active Development |
| **RetailersHubConfigServer** | Centralized external configuration for microservices | ✅ Implemented |
| **ApiGateway** | Centralized routing and entry point for microservices | 🚧 Active Development |
| **ServiceRegistry** | Service discovery and registration | 📋 Planned |
| **RolePermissionService** | Roles and permission management | 📋 Planned |
| **RetailerService** | Retailer/vendor management | 📋 Planned |
| **OrderService** | Order creation and lifecycle management | 📋 Planned |
| **PaymentService** | Payment and transaction management | 📋 Planned |
| **Kafka** | Event-driven asynchronous communication | 📋 Planned |
| **Redis** | Distributed caching and low-latency data access | 📋 Planned |

---

# ⚙️ Centralized Configuration

RetailersHub now includes a dedicated **Spring Cloud Config Server** for centralized configuration management.

```text
                 ┌─────────────────────────┐
                 │     Git Repository      │
                 │  Centralized Config     │
                 └────────────┬────────────┘
                              │
                              ▼
                 ┌─────────────────────────┐
                 │      Config Server      │
                 │  Spring Cloud Config   │
                 └────────────┬────────────┘
                              │
             ┌────────────────┼────────────────┐
             │                │                │
             ▼                ▼                ▼
       Authentication     User Service    Product Service
          Service
             │
             ▼
       Notification Service
```

### Configuration approach

- Spring Cloud Config Server
- Spring Cloud Config Client
- Git-backed configuration
- Centralized application properties
- Environment-specific configuration
- Configuration separated from application code
- Services retrieve configuration from the Config Server during startup

This reduces configuration duplication and provides a centralized mechanism for managing service configuration.

### Current configuration flow

```text
Microservice
     │
     │ Config Client
     ▼
Config Server
     │
     ▼
Git Repository
     │
     ▼
Application-specific configuration
```

---

# 🌐 API Gateway

RetailersHub is being integrated with **Spring Cloud Gateway** to provide a centralized entry point for client requests.

```text
Client
   │
   ▼
API Gateway
   │
   ├──────────────► AuthenticationService
   │
   ├──────────────► UserService
   │
   ├──────────────► ProductService
   │
   └──────────────► NotificationService
```

The Gateway is intended to provide:

- Centralized request routing
- Service abstraction from clients
- Common request filtering
- Authentication/security integration
- Centralized cross-cutting concerns
- A single entry point for external clients

> Gateway implementation is currently under active development.

---

# 🔐 Authentication & Authorization

Authentication is handled through a dedicated **AuthenticationService**.

Current authentication responsibilities include:

- User registration
- User login
- JWT generation
- JWT validation
- Password handling
- Account verification workflows
- OTP-related verification
- Forgot-password workflow
- Authentication-related validation
- Secure access to protected APIs

### JWT flow

```text
                 ┌──────────────┐
                 │    Client    │
                 └──────┬───────┘
                        │
                  Login Request
                        │
                        ▼
             ┌────────────────────┐
             │ Authentication     │
             │ Service            │
             └─────────┬──────────┘
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

Subsequent protected requests use:

```http
Authorization: Bearer <JWT>
```

---

# 👥 Core User Types

RetailersHub is designed around multiple actors in the retail ecosystem.

### Super Admin

Responsible for overall platform administration.

Typical responsibilities:

- Manage administrators
- Manage platform configuration
- Manage roles and permissions
- Monitor system activity
- Manage retailers

### Admin

Responsible for operational management.

Typical responsibilities:

- Manage users
- Manage products
- Manage retailers
- Manage orders
- View operational information

### Retailer

Responsible for retail operations.

Typical responsibilities:

- Manage products
- Update inventory
- View orders
- Manage pricing
- Monitor sales

### Product Manager

Responsible for product catalog operations.

Typical responsibilities:

- Create products
- Update products
- Manage categories
- Manage inventory
- Update product information

### Order Manager

Responsible for order processing.

Typical responsibilities:

- View orders
- Process orders
- Update order status
- Handle cancellations
- Manage fulfillment workflows

### Customer

The end user of the retail platform.

Typical responsibilities:

- Register/login
- Browse products
- Search products
- Place orders
- Track orders
- Manage profile
- Receive notifications

---

# 🛠️ Technology Stack

## Backend

- **Java**
- **Spring Boot**
- **Spring Security**
- **Spring Cloud**
- **Spring Data JPA**
- **Hibernate**
- **REST APIs**
- **Maven**
- **WebClient**

## Security

- JWT
- Spring Security
- Password hashing
- Role-based authorization
- Input validation

## Databases

- MySQL
- Oracle
- PostgreSQL

The project follows a service-owned data approach, where individual services are responsible for their own persistence.

## Configuration

- Spring Cloud Config Server
- Spring Cloud Config Client
- Git-backed configuration
- Environment-specific properties

## Containerization

- Docker
- Docker Compose
- Dockerfile
- Google Jib
- Containerized Spring Boot services

## Version Control

- Git
- GitHub

## Planned Infrastructure

- Apache Kafka
- Redis
- Service Discovery
- Distributed tracing
- Prometheus
- Grafana

---

# 📁 Repository Structure

RetailersHub is maintained as a **monorepo**, allowing the microservices and supporting components to be managed from a single GitHub repository.

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
├── ProductService/
│   ├── pom.xml
│   └── src/
│
├── NotificationService/
│   ├── pom.xml
│   └── src/
│
├── RetailersHubConfigServer/
│   ├── pom.xml
│   └── src/
│
├── ApiGateway/
│   └── src/
│
└── infrastructure/
    ├── docker/
    ├── docker-compose.yml
    └── configuration/
```

> The exact directory structure may evolve as additional microservices are introduced.

---

# 🔄 Service-to-Service Communication

The current implementation uses **synchronous REST-based communication**, including Spring WebClient where service-to-service calls are required.

Example:

```text
AuthenticationService
        │
        │ WebClient / REST
        ▼
   UserService
        │
        │ WebClient / REST
        ▼
NotificationService
```

Example request flow:

```text
Client
  │
  ▼
AuthenticationService
  │
  ├── Create/validate authentication data
  │
  ▼
UserService
  │
  └── User operation
  │
  ▼
NotificationService
  │
  └── Send notification
```

### Future asynchronous communication

Apache Kafka is planned for event-driven workflows such as:

```text
Order Created
     │
     ▼
   Kafka
     │
     ├──────────────► Notification Service
     │
     ├──────────────► Payment Service
     │
     └──────────────► Inventory Service
```

Kafka is therefore treated as a **planned event-driven communication layer**, not the current primary communication mechanism.

---

# 🗄️ Database Strategy

RetailersHub follows the **database-per-service** principle.

```text
AuthenticationService ──► Authentication DB

UserService ────────────► User DB

ProductService ─────────► Product DB

OrderService ───────────► Order DB

PaymentService ─────────► Payment DB
```

Services should communicate through APIs or events rather than directly accessing another service's database.

Benefits include:

- Service isolation
- Reduced coupling
- Independent schema evolution
- Independent scaling
- Clear ownership of business data

---

# 📡 API Design

Services expose RESTful APIs.

### Authentication APIs

```http
POST /auth/register
POST /auth/login
POST /auth/forgot-password-request
POST /auth/reset-password
```

### User APIs

```http
POST   /userService/createNewUser
GET    /userService/{id}
PUT    /userService/{id}
DELETE /userService/{id}
```

API documentation is intended to be provided through **OpenAPI/Swagger**.

Typical endpoints:

```text
/swagger-ui/index.html
/v3/api-docs
```

---

# 🐳 Docker & Containerization

RetailersHub services are being containerized for consistent local and deployment environments.

The project uses:

- Docker
- Dockerfiles
- Docker Compose
- Google Jib
- Containerized Spring Boot applications

### Jib

Google Jib is used to build container images directly from Maven without requiring a traditional Dockerfile-based image build for every service.

Example workflow:

```text
Maven Build
    │
    ▼
Google Jib
    │
    ▼
Container Image
    │
    ▼
Docker Registry
```

Example image naming convention:

```text
amitcodedocker/<service-name>:<version>
```

### Docker Compose

Docker Compose is used to orchestrate multiple services and supporting infrastructure locally.

```text
                    Docker Compose
                          │
        ┌─────────────────┼─────────────────┐
        │                 │                 │
        ▼                 ▼                 ▼
 Authentication       User Service     Notification
   Service              Service          Service
        │                 │                 │
        └─────────────────┼─────────────────┘
                          │
                          ▼
                    Config Server
```

---

# ⚙️ Local Development

## Prerequisites

Install the following:

- Java
- Maven
- Git
- Docker
- Docker Compose
- MySQL / PostgreSQL as required by the service
- IntelliJ IDEA or another Java IDE

---

# 🚀 Running a Service Locally

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

The same approach can be used for other Spring Boot services.

---

# 🐳 Running With Docker

Build and start the multi-service environment:

```bash
docker compose up --build
```

To run in detached mode:

```bash
docker compose up -d --build
```

To view running containers:

```bash
docker ps
```

To view service logs:

```bash
docker compose logs -f
```

To stop the environment:

```bash
docker compose down
```

---

# 🔧 Configuration in Docker

When services run inside Docker Compose, service-to-service communication must use the Docker Compose service names rather than `localhost`.

Example:

```text
AuthenticationService
        │
        │
        ▼
http://configserver:8092
        │
        ▼
Config Server
```

This allows containers to communicate through the Docker Compose network.

For local execution outside Docker, the corresponding local host configuration can be used.

---

# 🧪 Testing

The project is being developed with automated testing in mind.

Testing areas include:

- Unit testing
- Controller testing
- Service-layer testing
- Repository testing
- Integration testing
- Security testing

Run tests using:

```bash
mvn test
```

---

# 🔒 Security Principles

The project follows common backend security practices:

- Stateless authentication
- JWT-based authentication
- Password hashing
- Protected REST endpoints
- Role-based authorization
- Input validation
- Exception handling
- Environment-based configuration
- Secrets excluded from source control
- Secure service-to-service communication

---

# 📈 Development Roadmap

| Feature | Status |
|---|---|
| Authentication Service | ✅ Implemented / Active Development |
| User Service | ✅ Implemented / Active Development |
| Notification Service | 🚧 Active Development |
| Product Service | 🚧 Active Development |
| Spring Cloud Config Server | ✅ Implemented |
| Centralized Git-backed Configuration | ✅ Implemented |
| Docker Containerization | ✅ Implemented / Active Development |
| Google Jib | ✅ Implemented |
| Docker Compose | 🚧 Active Development |
| API Gateway | 🚧 Active Development |
| Role & Permission Management | 📋 Planned |
| Retailer Service | 📋 Planned |
| Order Service | 📋 Planned |
| Payment Service | 📋 Planned |
| Service Discovery / Eureka | 📋 Planned |
| Kafka Event Processing | 📋 Planned |
| Redis Caching | 📋 Planned |
| Distributed Tracing | 📋 Planned |
| Monitoring & Observability | 📋 Planned |
| CI/CD Pipeline | 📋 Planned |
| Automated Integration Tests | 🚧 In Progress |

Legend:

- ✅ Implemented
- 🚧 Active Development / In Progress
- 📋 Planned

---

# 📊 Future Observability

The project is planned to introduce centralized observability.

```text
Application Services
        │
        ├── Logs
        ├── Metrics
        └── Traces
                │
                ▼
       Observability Platform
                │
        ┌───────┼────────┐
        ▼       ▼        ▼
      Logs    Metrics   Traces
```

Potential technologies:

- Spring Boot Actuator
- Micrometer
- Prometheus
- Grafana
- Distributed tracing

---

# 🔄 CI/CD

CI/CD automation is planned using GitHub Actions.

Target pipeline:

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
Docker/Jib Image Build
   │
   ▼
Image Registry
   │
   ▼
Deployment
```

The project can also be integrated with static code analysis and quality gates as the CI/CD pipeline evolves.

---

# 🧠 Key Concepts Demonstrated

RetailersHub is being developed to demonstrate practical backend and distributed-system concepts including:

### Java

- Object-Oriented Programming
- Collections Framework
- Exception Handling
- Java Streams
- Multithreading
- Modern Java features

### Spring

- Spring Boot
- Spring Core
- Spring Security
- Spring Data JPA
- Hibernate
- REST APIs
- WebClient
- Spring Cloud

### Microservices

- Service decomposition
- API Gateway
- Centralized configuration
- Service-to-service communication
- Database-per-service
- Distributed systems concepts
- Event-driven architecture

### Security

- JWT
- Authentication
- Authorization
- Role-based access control
- Password security

### DevOps / Infrastructure

- Git
- GitHub
- Docker
- Docker Compose
- Google Jib
- CI/CD
- Containerized deployment

### Planned Distributed Infrastructure

- Eureka / Service Discovery
- Kafka
- Redis
- Distributed tracing
- Monitoring and observability

---

# 📌 Why a Monorepo?

RetailersHub uses a monorepo structure so the complete application can be explored from a single repository.

```text
One Repository
      │
      ├── Authentication
      ├── Users
      ├── Products
      ├── Notifications
      ├── Config Server
      ├── API Gateway
      ├── Retailers
      ├── Orders
      └── Payments
```

Each service remains independently structured and deployable while the overall system can be versioned and reviewed together.

This also makes the project easier to develop, test, demonstrate, and share as a portfolio project.

---

# 🎯 Engineering Goals

### Scalability

Services can be independently scaled based on workload.

### Maintainability

Business capabilities are separated into independently maintainable services.

### Loose Coupling

Services communicate through well-defined APIs and, in future iterations, asynchronous events.

### Security

Authentication and authorization are implemented using Spring Security and JWT.

### Configuration Management

Application configuration is centralized using Spring Cloud Config and Git-backed configuration.

### Resilience

The architecture is being designed to isolate failures between services and introduce resilience patterns as the system evolves.

### Observability

Future iterations will introduce centralized logging, metrics, tracing, and monitoring.

---

# 👨‍💻 Author

**Amit Kumar Pandey**

Backend Software Engineer

### Primary Interests

- Java
- Spring Boot
- Microservices
- Distributed Systems
- Backend Architecture
- REST APIs
- Database Design
- Cloud & DevOps

---

# ⭐ Project

If you find this project useful or interesting, consider giving the repository a ⭐.

**Repository:**

https://github.com/AmitCode/RetailersHubApplication
