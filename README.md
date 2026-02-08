# Java Microservices Assignment – Inventory & Order Services

## 📌 Overview
- **Inventory Service (port 8080)**: Manages product batches with expiry dates and stock updates.
- **Order Service (port 8081)**: Places orders, checks availability in real-time, and updates inventory.

The services communicate via REST APIs. The design follows the **Factory Design Pattern** for extensibility, uses **Spring Data JPA with H2 in-memory database**, and integrates **Liquibase** for schema and data initialization.

---

## ⚙️ Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database 
- Liquibase (schema + CSV data loading)
- Maven
- JUnit 5 + Mockito (unit tests)
- @SpringBootTest (integration tests)
- Lombok

---

## 🚀 Project Setup

### 1. Clone Repository
```bash
git clone https://github.com/<your-username>/Koerber-Assignment-Shrawan.git
cd Koerber-Assignment-Shrawan

mvn clean install  (for each service seperaetly )
mvn spring-boot:run (for each service seperaetly )

## API Endpoints

# GET /inventory/{productId}
Returns inventory batches sorted by expiry date.
{
  "productId": 1001,
  "productName": "Laptop",
  "batches": [
    { "batchId": 1, "quantity": 50, "expiryDate": "2025-12-31" },
    { "batchId": 2, "quantity": 30, "expiryDate": "2026-03-15" }
  ]
}

# POST /inventory/update
Updates inventory after an order is placed.

# Order Service
POST /order
Places an order and updates inventory.
Request:
{
  "productId": 1002,
  "quantity": 3
}
Response:
{
  "orderId": 5012,
  "productId": 1002,
  "productName": "Smartphone",
  "quantity": 3,
  "status": "PLACED",
  "reservedFromBatchIds": [3],
  "message": "Order placed. Inventory reserved."
}





