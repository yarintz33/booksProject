# Recommended Book Service

This microservice provides book recommendations by calling the Book Service and sorting books by rating in descending order.

## Features

- GET `/api/recommendations` - Returns a list of books sorted by rating (highest first)
- Communicates with Book Service using Spring Cloud OpenFeign
- Runs on port 8081 by default
- Comprehensive error handling for service communication

## Prerequisites

- Java 21
- Maven
- Book Service running on port 8080

## Running the Service

1. Make sure the Book Service is running on port 8080
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The service will start on `http://localhost:8081`

## API Endpoints

### GET /api/recommendations

Returns a list of books sorted by rating in descending order.

**Response:**

```json
[
  {
    "id": 1,
    "title": "The Great Gatsby",
    "rating": 9
  },
  {
    "id": 2,
    "title": "To Kill a Mockingbird",
    "rating": 8
  }
]
```

## Configuration

The service can be configured using the following properties:

- `server.port` - Port to run the service on (default: 8081)
- `book.service.url` - URL of the Book Service (default: http://localhost:8080)

## Error Handling

The service includes comprehensive error handling with the following error responses:

### 503 Service Unavailable

```json
{
  "httpCode": 503,
  "message": "Unable to retrieve books from Book Service"
}
```

### 500 Internal Server Error

```json
{
  "httpCode": 500,
  "message": "Internal server error: Unexpected error occurred"
}
```

## Architecture

- **Controller**: Handles HTTP requests and responses
- **Service**: Contains business logic for recommendations
- **Client**: Feign client for communicating with Book Service
- **Model**: Data transfer objects
- **Exception Handler**: Global exception handling with custom error responses

## Project Structure

```
src/
├── main/
│   ├── java/com/yarin/recommendedbookservice/
│   │   ├── RecommendedbookserviceApplication.java
│   │   ├── controller/
│   │   │   └── RecommendationController.java
│   │   ├── service/
│   │   │   └── RecommendationService.java
│   │   ├── client/
│   │   │   └── BookServiceClient.java
│   │   ├── model/
│   │   │   └── Book.java
│   │   └── exception/
│   │       ├── ErrorResponse.java
│   │       ├── BookServiceUnavailableException.java
│   │       └── GlobalExceptionHandler.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/yarin/recommendedbookservice/
        └── RecommendedbookserviceApplicationTests.java
```

## Testing

### Run Tests

```bash
mvn test
```

### Test the Service

```bash
# Get recommended books
curl http://localhost:8081/api/recommendations
```

## Technologies

- **Spring Boot 3.2.0**
- **Spring Cloud OpenFeign**
- **Spring Boot Validation**
- **Maven**
- **Java 21**
