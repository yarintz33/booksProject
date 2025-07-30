# Book Service - Microservice

A microservice for managing books with MongoDB.

## System Requirements

- Java 21
- Maven 3.6+
- MongoDB (optional - embedded MongoDB is used for testing)

## Installation and Setup

### 1. Install MongoDB (Optional)

If you want to use a local MongoDB instance:

```bash
# Install MongoDB Community Edition
# Or use Docker:
docker run -d -p 27017:27017 --name mongodb mongo:latest
```

### 2. Run the Application

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will run on `http://localhost:8080`

## API Endpoints

### 1. Get All Books

```
GET /api/books
```

### 2. Get Book by ID

```
GET /api/books/{id}
```

### 3. Create New Book

```
POST /api/books
Content-Type: application/json

{
  "id": 1,
  "title": "Book Title",
  "rating": 8
}
```

## Error Handling

The service includes comprehensive error handling with the following responses:

### 400 Bad Request

```json
{
  "httpCode": 400,
  "message": "Invalid title - cannot be blank"
}
```

### 404 Not Found

```json
{
  "httpCode": 404,
  "message": "Book not found with id: 123"
}
```

### 500 Internal Server Error

```json
{
  "httpCode": 500,
  "message": "Internal server error: Database connection failed"
}
```

## Data Model

### Book Model

```json
{
  "id": 1, // integer (auto-generated)
  "title": "string", // string up to 50 characters (required)
  "rating": 8 // integer between 1 and 10 (required)
}
```

## Testing

### Run Tests

```bash
mvn test
```

### Integration Tests

Tests use embedded MongoDB and don't require external MongoDB.

## Technologies

- **Spring Boot 3.5.4**
- **Spring Data MongoDB**
- **MongoDB** (local or embedded)
- **Maven**
- **Java 21**

## Project Structure

```
src/
├── main/
│   ├── java/com/yarin/bookService/
│   │   ├── BookServiceApplication.java
│   │   ├── controller/
│   │   │   └── BookController.java
│   │   ├── model/
│   │   │   └── Book.java
│   │   ├── repository/
│   │   │   └── BookRepository.java
│   │   ├── service/
│   │   │   └── BookService.java
│   │   └── exception/
│   │       ├── ErrorResponse.java
│   │       ├── BookNotFoundException.java
│   │       ├── InvalidBookDataException.java
│   │       └── GlobalExceptionHandler.java
│   └── resources/
│       └── application.properties
└── test/
    ├── java/com/yarin/bookService/
    │   ├── BookServiceTests.java
    │   └── BookControllerTests.java
    └── resources/
        └── application-test.properties
```

## Configuration

### MongoDB

- **Host**: localhost
- **Port**: 27017
- **Database**: bookservice

### Server

- **Port**: 8080

## Usage Examples

### Create a New Book

```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "title": "Harry Potter and the Philosopher\'s Stone",
    "rating": 9
  }'
```

### Get All Books

```bash
curl http://localhost:8080/api/books
```

### Get Book by ID

```bash
curl http://localhost:8080/api/books/1
```
