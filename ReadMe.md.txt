E-Commerce Microservices: Order & Inventory SystemThis repository contains two Spring Boot microservices designed to manage e-commerce operations: Inventory Service and Order Service. The system uses a batch-based inventory management strategy, prioritizing products by expiry date.🏗 Architecture OverviewInventory Service: Manages product batches, stock levels, and expiry dates.Order Service: Handles customer orders and communicates with the Inventory Service to validate and deduct stock.Design Pattern: Implements the Factory Design Pattern in the Inventory Service to allow for extensible inventory handling logic (e.g., switching between FIFO, FEFO, or custom allocation strategies).Database: H2 In-Memory Database with Liquibase for automated schema generation and CSV data seeding.🚀 Getting StartedPrerequisitesJava 17 (Minimum Java 8)Maven 3.8+Installation & RunningClone the repository:Bashgit clone <your-repo-url>
cd ecommerce-microservices
Run Inventory Service (Port 8081):Bashcd inventory-service
mvn spring-boot:run
Run Order Service (Port 8082):Bashcd order-service
mvn spring-boot:run
🛠 API Documentation1. Inventory ServiceMethodEndpointDescriptionGET/inventory/{productId}Returns all batches for a product, sorted by expiry date (FEFO).POST/inventory/updateUpdates/Deducts stock after an order is placed.Sample Response (GET /inventory/1001):JSON{
  "productId": 1001,
  "productName": "Laptop",
  "batches": [
    { "batchId": 1, "quantity": 50, "expiryDate": "2026-06-25" }
  ]
}
2. Order ServiceMethodEndpointDescriptionPOST/orderValidates stock with Inventory Service and places an order.Sample Request (POST /order):JSON{
  "productId": 1002,
  "quantity": 3
}
Sample Response:JSON{
  "orderId": 1,
  "productId": 1002,
  "productName": "Smartphone",
  "quantity": 3,
  "status": "PLACED",
  "reservedFromBatchIds": [9],
  "message": "Order placed. Inventory reserved."
}
🧪 TestingBoth services include unit tests for business logic and integration tests for REST endpoints.Run all tests:Bashmvn test
Technologies used: JUnit 5, Mockito, @SpringBootTest.📂 Project Structure & DesignFactory Design PatternIn the Inventory Service, the InventoryHandlerFactory is used to instantiate specific inventory logic. This ensures that if the company decides to change how they handle "Expired Goods" vs "Standard Goods" in the future, the system remains loosely coupled.Data Management (Liquibase)The system uses Liquibase to manage the database lifecycle.Schema: Defined in db.changelog-master.xml.Data Seeding: On startup, the loadData tag imports the provided Sample CSV data for products and orders directly into the H2 database.Inter-service CommunicationThe Order Service utilizes WebClient (or RestTemplate) to perform synchronous calls to the Inventory Service. It ensures that an order is only marked as PLACED if the inventory update returns a success status.