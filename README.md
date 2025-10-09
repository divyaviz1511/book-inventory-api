# 📚 Book Inventory API

A simple backend API built with **Spring Boot** and **MySQL** to manage a bookstore inventory.  
It supports full CRUD operations and includes validation, custom annotations, and proper error handling.

---

## 🚀 Features

- Add, update, delete, and retrieve books
- Input validation (including custom validators for title and language)
- Global exception handling
- Layered architecture: Controller → Service → Repository → DB
- **RESTful API** using Spring Boot
- Search Logic using **JPA Specification** and predicates 
- **RabbitMQ** Messaging (via CloudAMQP) for low stock alerts.
- Ready with frontend integration (**React**) (https://github.com/divyaviz1511/bookstore-ui-react)


---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Jakarta Bean Validation
- Maven
- RabbitMQ


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

## FrontEnd (WIP)
Frontend will be built using React
