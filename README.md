# Product Inventory API

## Overview
This is a Spring Boot REST API for managing products and their avaibility across warehouses.

## Prerequisites
- Java 17
- Maven

## Running the Application
1. Clone the repository.
2. Navigate to the project directory.
3. Build project
 mvn clean install
4. Run the application:
```bash
mvn spring-boot:run
The application will start at http://localhost:8080.
Run the unit tests:
 mvn test
Run the integration tests:
mvn verify
API Testing
You can use Postman or Curl to test the API endpoints.

Example Requests
Get all products:
curl -X GET http://localhost:8080/api/products
Create a product:
curl -X POST http://localhost:8080/api/products \
-H "Content-Type: application/json" \
-d '{"name": "New Product", "price": 50.00}'

