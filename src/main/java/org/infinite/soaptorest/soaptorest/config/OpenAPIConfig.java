package org.infinite.soaptorest.soaptorest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Swagger/OpenAPI Configuration
 * Provides comprehensive API documentation
 */
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce REST API")
                        .version("1.0.0")
                        .description("RESTful API for E-Commerce Platform\n\n" +
                                "This API is converted from SOAP/WSDL to REST using Spring Boot.\n\n" +
                                "**Key Features:**\n" +
                                "- Customer Management (CRUD)\n" +
                                "- Product Catalog Operations\n" +
                                "- Order Management\n" +
                                "- Inventory Tracking\n" +
                                "- Promotion & Coupon Validation\n\n" +
                                "**Exception Handling:**\n" +
                                "All errors are returned with proper HTTP status codes and structured error responses.\n\n" +
                                "**Response Format:**\n" +
                                "All responses include a ResponseHeader with requestId, timestamp, status, and errors (if any).")
                        .contact(new Contact()
                                .name("API Support")
                                .email("support@ecommerce.example.com")
                                .url("https://ecommerce.example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local Development Server"),
                        new Server()
                                .url("https://api.ecommerce.example.com")
                                .description("Production Server")
                ));
    }
}

