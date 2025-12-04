# 📚 Book Inventory API Service

A high-performance Java Spring Boot microservice that powers the core backend for the Book Inventory System.
Provides CRUD operations for books, exposes REST APIs, manages multi-layer caching, and publishes domain events to RabbitMQ for downstream AI/NLP services. Also includes API input validation, custom annotations, and proper error handling.

This service integrates with:
- React UI (frontend)
- Python FastAPI microservice (semantic search + ML pricing)
- RabbitMQ (event-driven communication)
- Redis (for caching)
---

## 🚀 Features

- Full CRUD operations for book entities
- RESTful endpoints for creating, updating, deleting, and retrieving books
- Input validation, error handling, and transactional safety

Multi-Layer Caching (Redis + Caffeine)
- Designed for high throughput and low latency:
- Redis — cache for full book records (distributed caching)
- Caffeine — in-memory cache for: individual book lookups, low-stock alerts frequently accessed reference data
- Automatic cache sync on updates & deletions
- Significantly reduces database load

RabbitMQ Event Publishing
- Publishes events for other services to consume: book.added, book.updated, book.deleted
- The Python FastAPI service listens to these events to refresh semantic embeddings and pricing metadata.

Reactive HTTP Client (WebFlux)
- Includes Spring WebFlux WebClient to call external microservices (Python API).
Used for: querying ML Best Seller confidence parameter
(These calls currently support in-progress features like “best seller lookup,” which may be refined or removed as the project evolves.)

Clean Architecture
- Service layer + repository layer separation
- DTOs for clean API contracts
- Exception handling via global error handlers
---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Jakarta Bean Validation
- Maven
- RabbitMQ
- Redis & Caffeine caching
- Spring WebFlux (WebClient)

## 📬 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/book_details` | Get all books |
| `GET` | `/api/book_details/{id}` | Get book by ID |
| `POST` | `/api/book_details` | Add new book |
| `PATCH` | `/api/book_details/{id}` | Update book (partial or full) |
| `DELETE` | `/api/book_details/{id}` | Delete book by ID |
| `POST` | `/api/book_details/search` | Book Search by criteria |

---

## 🧪 Validations

- **Title**: Must start with a letter/number and allow only basic characters (:-.?')
- **Language**: Only specific values allowed (`English`, `French`, etc.)
- **Custom Annotations**: 
  - `@ValidBookTitle`
  - `@ValidLanguage`

---

## 🔗 Frontend Repository

The visual feel of the API is built using **React + Bootstrap + Axios(for Async API)** and can be found here:

👉 [React Book Inventory UI] [https://github.com/divyaviz1511/bookstore-ui-react](https://github.com/divyaviz1511/bookstore-ui-react)

---

## 🛠️ Setup Instructions

### ✅ Prerequisites

- Java 17+
- Maven
- MySQL (local or Docker)

### ⚙️ Setup

1. Clone the repo:

```bash
git clone https://github.com/your-username/book-inventory-api.git
cd book-inventory-api
```

2. Update your applications.properties
```spring.datasource.url=jdbc:mysql://localhost:3306/bookinventory_db
spring.datasource.username=your_mysql_user
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=none
```

3. Build and run
```mvn clean install
mvn spring-boot:run
```
