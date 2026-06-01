# SOAP to REST Conversion - E-Commerce Platform

## Overview

This project converts a complete SOAP-based e-commerce web service to a RESTful API using Spring Boot. All SOAP operations from the WSDL are mapped to equivalent REST endpoints with proper exception handling based on SOAP fault types.

## Architecture

### Project Structure

```
src/main/java/org/infinite/soaptorest/soaptorest/
├── controller/           # REST Controllers (mapped from SOAP PortTypes)
│   ├── CustomerController
│   ├── ProductController
│   ├── OrderController
│   ├── InventoryController
│   └── PromotionController
├── service/             # Business Logic Services (mock implementation)
│   ├── CustomerService
│   ├── ProductService
│   ├── OrderService
│   ├── InventoryService
│   └── PromotionService
├── dto/                 # Data Transfer Objects (mapped from XSD types)
│   ├── common/         # Shared DTOs
│   ├── customer/       # Customer domain DTOs
│   ├── product/        # Product domain DTOs
│   ├── order/          # Order domain DTOs
│   ├── inventory/      # Inventory domain DTOs
│   └── promotion/      # Promotion domain DTOs
├── exception/           # Custom Exception Classes
│   ├── ServiceException
│   ├── ValidationException
│   ├── ResourceNotFoundException
│   ├── BusinessException
│   ├── InventoryException
│   └── PaymentException
├── handler/            # Global Exception Handler
│   ├── GlobalExceptionHandler
│   └── ErrorResponse
├── util/               # Utility Classes
│   └── ResponseHeaderUtil
└── SoapTorestApplication.java
```

## SOAP to REST Mapping

### Customer Operations (SOAP: CustomerPortType)

| SOAP Operation | REST Method | Endpoint | Request | Response |
|---|---|---|---|---|
| CreateCustomer | POST | `/api/v1/customers` | CreateCustomerRequest | CreateCustomerResponse |
| GetCustomer | GET | `/api/v1/customers/{customerId}` | - | GetCustomerResponse |
| UpdateCustomer | PATCH | `/api/v1/customers/{customerId}` | UpdateCustomerRequest | UpdateCustomerResponse |
| SearchCustomers | GET | `/api/v1/customers?email=&status=&tier=&page=&size=` | Query params | SearchCustomersResponse |

### Product Operations (SOAP: CatalogPortType)

| SOAP Operation | REST Method | Endpoint | Request | Response |
|---|---|---|---|---|
| CreateProduct | POST | `/api/v1/products` | CreateProductRequest | CreateProductResponse |
| GetProduct | GET | `/api/v1/products/{productId}` | - | GetProductResponse |
| SearchProducts | GET | `/api/v1/products?keyword=&categoryId=&minPrice=&maxPrice=&page=&size=` | Query params | SearchProductsResponse |

### Order Operations (SOAP: OrderPortType)

| SOAP Operation | REST Method | Endpoint | Request | Response |
|---|---|---|---|---|
| PlaceOrder | POST | `/api/v1/orders` | PlaceOrderRequest | PlaceOrderResponse |
| GetOrder | GET | `/api/v1/orders/{orderId}` | - | GetOrderResponse |
| UpdateOrderStatus | PATCH | `/api/v1/orders/{orderId}/status` | UpdateOrderStatusRequest | UpdateOrderStatusResponse |

### Inventory Operations (SOAP: InventoryPortType)

| SOAP Operation | REST Method | Endpoint | Request | Response |
|---|---|---|---|---|
| GetInventoryLevel | GET | `/api/v1/inventory?variantId=&warehouseId=` | Query params | GetInventoryLevelResponse |
| AdjustInventory | POST | `/api/v1/inventory/adjustments` | AdjustInventoryRequest | AdjustInventoryResponse |

### Promotion Operations (SOAP: PromotionPortType)

| SOAP Operation | REST Method | Endpoint | Request | Response |
|---|---|---|---|---|
| ValidateCoupon | POST | `/api/v1/promotions/coupons/validate` | ValidateCouponRequest | ValidateCouponResponse |

## Exception Handling

All exceptions are mapped to appropriate HTTP status codes and returned as structured error responses (equivalent to SOAP faults):

### Exception Mappings

| Exception Type | HTTP Status | SOAP Equivalent |
|---|---|---|
| `ServiceException` | 500 | ServiceFault |
| `ValidationException` | 400 | Validation Fault |
| `ResourceNotFoundException` | 404 | Resource Not Found Fault |
| `BusinessException` | 422 | Business Logic Fault |
| `InventoryException` | 422 | Inventory Fault |
| `PaymentException` | 402 | Payment Required Fault |
| `MethodArgumentNotValidException` | 400 | Validation Fault |

### Error Response Format

```json
{
  "header": {
    "requestId": "550e8400-e29b-41d4-a716-446655440000",
    "timestamp": "2024-05-25T10:30:45.123456",
    "status": "FAILURE",
    "errors": [
      {
        "errorCode": "VALIDATION_ERROR",
        "errorMessage": "Email is required",
        "field": "email",
        "timestamp": "2024-05-25T10:30:45.123456"
      }
    ]
  }
}
```

## Response Format

All successful responses follow this structure (maps to SOAP response messages):

```json
{
  "header": {
    "requestId": "550e8400-e29b-41d4-a716-446655440000",
    "timestamp": "2024-05-25T10:30:45.123456",
    "status": "SUCCESS",
    "errors": null
  },
  "customer": {
    "customerId": "550e8400-e29b-41d4-a716-446655440001",
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phone": "+1234567890",
    "status": "ACTIVE",
    "tier": "GOLD",
    "createdAt": "2024-05-25T10:30:45.123456",
    "updatedAt": "2024-05-25T10:30:45.123456"
  }
}
```

## DTO Mappings from XSD Types

### Common Types

- `common-types.xsd` → `dto/common/`
  - `ResponseHeader` (from ResponseHeaderType)
  - `ServiceError` (from ServiceErrorType)
  - `PageMeta` (from PageMetaType)
  - `StatusCode` (from StatusCodeType)

### Customer Types

- `customer-types.xsd` → `dto/customer/`
  - `CustomerDTO` (from CustomerType)
  - `CustomerStatus` (enum from CustomerStatusType)
  - `CustomerTier` (enum from CustomerTierType)
  - `CreateCustomerRequest/Response`
  - `GetCustomerRequest/Response`
  - `UpdateCustomerRequest/Response`
  - `SearchCustomersRequest/Response`

### Product Types

- `catalog-types.xsd` → `dto/product/`
  - `ProductDTO` (from ProductType)
  - `ProductStatus` (enum)
  - `ProductType` (enum)
  - `CreateProductRequest/Response`
  - `GetProductRequest/Response`
  - `SearchProductsRequest/Response`

### Order Types

- `order-types.xsd` → `dto/order/`
  - `OrderDTO` (from OrderType)
  - `OrderStatus` (enum from OrderStatusType)
  - `OrderItem` (from order line item)
  - `PlaceOrderRequest/Response`
  - `GetOrderRequest/Response`
  - `UpdateOrderStatusRequest/Response`

### Inventory Types

- `inventory-types.xsd` → `dto/inventory/`
  - `InventoryLevelDTO` (from InventoryLevel)
  - `GetInventoryLevelRequest/Response`
  - `AdjustInventoryRequest/Response`

### Promotion Types

- Coupon/Promotion validation DTOs
  - `ValidateCouponRequest/Response`

## Building and Running

### Prerequisites

- Java 17+
- Maven 3.6+

### Build

```bash
./mvnw clean package
```

### Run

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## API Examples

### Create Customer

```bash
curl -X POST http://localhost:8080/api/v1/customers \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phone": "+1234567890"
  }'
```

### Get Customer

```bash
curl http://localhost:8080/api/v1/customers/550e8400-e29b-41d4-a716-446655440001
```

### Search Customers

```bash
curl "http://localhost:8080/api/v1/customers?status=ACTIVE&tier=GOLD&pageNumber=1&pageSize=20"
```

### Create Product

```bash
curl -X POST http://localhost:8080/api/v1/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop",
    "description": "High-performance laptop",
    "brand": "TechBrand",
    "productType": "PHYSICAL",
    "basePrice": 999.99,
    "categoryIds": ["550e8400-e29b-41d4-a716-446655440002"]
  }'
```

### Place Order

```bash
curl -X POST http://localhost:8080/api/v1/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": "550e8400-e29b-41d4-a716-446655440001",
    "items": [
      {
        "productId": "550e8400-e29b-41d4-a716-446655440003",
        "quantity": 2,
        "variantId": "variant-001"
      }
    ],
    "couponCode": "COUPON10",
    "shippingMethod": "STANDARD"
  }'
```

### Validate Coupon

```bash
curl -X POST http://localhost:8080/api/v1/promotions/coupons/validate \
  -H "Content-Type: application/json" \
  -d '{
    "couponCode": "COUPON10",
    "cartTotal": 150.00
  }'
```

### Adjust Inventory

```bash
curl -X POST http://localhost:8080/api/v1/inventory/adjustments \
  -H "Content-Type: application/json" \
  -d '{
    "variantId": "550e8400-e29b-41d4-a716-446655440004",
    "warehouseId": "550e8400-e29b-41d4-a716-446655440005",
    "quantityDelta": 100,
    "reason": "Received from supplier"
  }'
```

## Key Features

### ✅ Complete SOAP to REST Mapping
- All SOAP operations converted to REST endpoints
- Proper HTTP methods (POST, GET, PATCH)
- Query parameters for search/filtering
- Path parameters for resource IDs

### ✅ Exception Handling Based on SOAP Faults
- Custom exception hierarchy
- Structured error responses matching SOAP fault format
- Appropriate HTTP status codes
- Detailed error messages with field-level validation

### ✅ Mock Database Implementation
- In-memory storage using HashMap
- Ready for database integration
- Service layer abstraction for easy replacement

### ✅ Response Envelopes
- Every response includes RequestId and Timestamp
- Standard response headers (maps to SOAP ResponseHeader)
- Pagination metadata for list responses
- Status codes (SUCCESS, FAILURE, PARTIAL, etc.)

### ✅ Input Validation
- Bean validation annotations (@Valid, @NotNull, @Email, etc.)
- Field-level error reporting
- Validation exception handling

### ✅ REST Best Practices
- Proper HTTP status codes
- RESTful endpoint design
- Resource-oriented architecture
- Standard JSON request/response format

## Database Integration

Currently, the application uses an in-memory HashMap for storage. To integrate with a real database:

1. Create JPA entities mapping to DTOs
2. Create Spring Data repositories
3. Replace mock services with database queries
4. Add `spring-boot-starter-data-jpa` and database driver to `pom.xml`

Example:

```java
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
        CustomerEntity entity = new CustomerEntity();
        // Map request to entity
        repository.save(entity);
        // Map entity back to DTO
    }
}
```

## Next Steps

1. **Add Database Layer**: Implement JPA/Hibernate entities and repositories
2. **Add Authentication**: Implement JWT or OAuth2 (SOAP had WS-Security)
3. **Add Logging**: Use SLF4J for comprehensive logging
4. **Add Metrics**: Add Micrometer for monitoring
5. **Add API Documentation**: Swagger/OpenAPI integration
6. **Add Unit Tests**: JUnit 5 and Mockito tests
7. **Add Integration Tests**: TestContainers for database testing
8. **Add Async Processing**: For long-running operations

## SOAP to REST Conversion Summary

| Aspect | SOAP (WSDL) | REST |
|---|---|---|
| **Protocol** | XML over SOAP/HTTP | JSON over HTTP |
| **Interface Definition** | WSDL | OpenAPI/Swagger |
| **Operations** | RPC-style with messages | Resource-based with HTTP methods |
| **Faults** | SOAP faults in XML | HTTP status codes + JSON errors |
| **Request/Response** | Wrapped in SOAP envelope | Direct JSON payload |
| **Error Handling** | ServiceFault type | Multiple exception types → HTTP status |
| **Security** | WS-Security | OAuth2/JWT (can be added) |
| **Database** | Not in WSDL | Implementation in services |
| **Transactions** | Implicit in SOAP | HTTP idempotence + status codes |

---

**Project Version**: 1.0.0  
**Created**: 2024-05-25  
**Spring Boot Version**: 4.0.6  
**Java Version**: 17+

