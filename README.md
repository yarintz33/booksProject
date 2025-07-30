# Book Project - Microservices Architecture

This project demonstrates a microservices architecture with two Spring Boot services that communicate with each other.

## Project Overview

The project consists of two microservices:

1. **BookService** - Manages book data with MongoDB
2. **RecommendedBookService** - Provides book recommendations by calling the BookService

## Architecture

```
┌─────────────────┐    HTTP/REST    ┌─────────────────────┐
│   BookService   │ ◄──────────────► │ RecommendedBook     │
│   (Port 8080)   │                 │ Service (Port 8081) │
│                 │                 │                     │
│ - MongoDB       │                 │ - Feign Client      │
│ - CRUD Ops      │                 │ - Sorting Logic     │
│ - Validation    │                 │ - Error Handling    │
└─────────────────┘                 └─────────────────────┘
```

## Services

### 1. BookService

**Port:** 8080  
**Database:** MongoDB  
**Features:**

- CRUD operations for books
- Data validation
- Global error handling
- RESTful API

**Endpoints:**

- `GET /api/books` - Get all books
- `GET /api/books/{id}` - Get book by ID
- `POST /api/books` - Create a new book

**Data Model:**

```json
{
  "id": 1,
  "title": "Book Title",
  "rating": 8
}
```

### 2. RecommendedBookService

**Port:** 8081  
**Features:**

- Calls BookService to retrieve books
- Sorts books by rating (descending)
- Returns recommended book list
- Error handling for service communication

**Endpoints:**

- `GET /api/recommendations` - Get recommended books sorted by rating

## Technology Stack

- **Java 21**
- **Spring Boot 3.2.0**
- **Spring Data MongoDB**
- **Spring Cloud OpenFeign**
- **MongoDB** (local or embedded for testing)
- **Maven**

## Prerequisites

- Java 21
- Maven 3.6+
- MongoDB (optional - embedded MongoDB is used for testing)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/bookProject.git
cd bookProject
```

### 2. Start MongoDB (Optional)

If you want to use a local MongoDB instance:

```bash
# Using Docker
docker run -d -p 27017:27017 --name mongodb mongo:latest

# Or install MongoDB Community Edition
```

### 3. Run the Services

#### Start BookService

```bash
cd bookService
./mvnw spring-boot:run
```

The service will start on `http://localhost:8080`

#### Start RecommendedBookService

```bash
cd recommendedBookService
./mvnw spring-boot:run
```

The service will start on `http://localhost:8081`

### 4. Test the Services

#### Test BookService

```bash
# Get all books
curl http://localhost:8080/api/books

# Create a new book
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "title": "The Great Gatsby",
    "rating": 9
  }'

# Get book by ID
curl http://localhost:8080/api/books/1
```

#### Test RecommendedBookService

```bash
# Get recommended books
curl http://localhost:8081/api/recommendations
```

## Error Handling

Both services include comprehensive error handling with standardized error responses:

### Error Response Format

```json
{
  "httpCode": 400,
  "message": "Error description"
}
```

### Common Error Codes

- **400** - Bad Request (validation errors)
- **404** - Not Found (resource not found)
- **500** - Internal Server Error
- **503** - Service Unavailable (service communication errors)

## Testing

### Run Tests

```bash
# Test BookService
cd bookService
./mvnw test

# Test RecommendedBookService
cd recommendedBookService
./mvnw test
```

### Test Coverage

- Unit tests for services
- Integration tests with embedded MongoDB
- Error handling tests
- Service communication tests

## Configuration

### BookService Configuration

```properties
# Server
server.port=8080

# MongoDB
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=bookservice
```

### RecommendedBookService Configuration

```properties
# Server
server.port=8081

# Book Service URL
book.service.url=http://localhost:8080
```

## Project Structure

```
bookProject/
├── bookService/
│   ├── src/
│   │   ├── main/java/com/yarin/bookService/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   └── exception/
│   │   └── resources/
│   └── pom.xml
├── recommendedBookService/
│   ├── src/
│   │   ├── main/java/com/yarin/recommendedbookservice/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── client/
│   │   │   ├── model/
│   │   │   └── exception/
│   │   └── resources/
│   └── pom.xml
└── README.md
```

## Development

### Adding New Features

1. **BookService**: Add new endpoints in `BookController`
2. **RecommendedBookService**: Add new client methods in `BookServiceClient`

### Error Handling

- Add new exception classes in the `exception` package
- Update `GlobalExceptionHandler` to handle new exceptions
- Follow the standardized error response format

### Testing

- Write unit tests for new services
- Add integration tests for new endpoints
- Test error scenarios

## Deployment

### Docker (Optional)

```bash
# Build BookService
cd bookService
./mvnw clean package
docker build -t bookservice .

# Build RecommendedBookService
cd ../recommendedBookService
./mvnw clean package
docker build -t recommendedbookservice .

# Run with Docker Compose
docker-compose up
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## License

This project is licensed under the MIT License.

## Support

For questions or issues, please create an issue in the GitHub repository.
