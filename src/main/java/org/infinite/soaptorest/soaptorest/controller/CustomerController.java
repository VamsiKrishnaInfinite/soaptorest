package org.infinite.soaptorest.soaptorest.controller;

import org.infinite.soaptorest.soaptorest.dto.customer.*;
import org.infinite.soaptorest.soaptorest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Customer REST Controller
 * Maps SOAP CustomerPortType operations to REST endpoints
 */
@RestController
@RequestMapping("/api/v1/customers")
@Validated
@Tag(name = "Customers", description = "Customer management operations - SOAP CreateCustomer, GetCustomer, UpdateCustomer, SearchCustomers")
public class CustomerController{
        @Autowired
    private CustomerService customerService;

    /**
     * POST /api/v1/customers
     * Create a new customer - maps to SOAP CreateCustomer
     */
    @PostMapping
    @Operation(summary = "Create a new customer", description = "Registers a new customer account. Sends verification email upon success. Maps to SOAP CreateCustomer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created successfully", content = @Content(schema = @Schema(implementation = CreateCustomerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CreateCustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(request));
    }

    /**
     * GET /api/v1/customers/{customerId}
     * Get customer by ID - maps to SOAP GetCustomer
     */
    @GetMapping("/{customerId}")
    @Operation(summary = "Get customer by ID", description = "Retrieves a customer by their ID. Maps to SOAP GetCustomer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found", content = @Content(schema = @Schema(implementation = GetCustomerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GetCustomerResponse> getCustomer(
            @Parameter(description = "Customer ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440001")
            @PathVariable UUID customerId) {
        GetCustomerRequest request = GetCustomerRequest.builder().customerId(customerId).build();
        return ResponseEntity.ok(customerService.getCustomer(request));
    }

    /**
     * PATCH /api/v1/customers/{customerId}
     * Update customer - maps to SOAP UpdateCustomer
     */
    @PatchMapping("/{customerId}")
    @Operation(summary = "Update customer details", description = "Partially update a customer profile (PATCH semantics; null fields are ignored). Maps to SOAP UpdateCustomer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully", content = @Content(schema = @Schema(implementation = UpdateCustomerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UpdateCustomerResponse> updateCustomer(
            @Parameter(description = "Customer ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440001")
            @PathVariable UUID customerId,
            @Valid @RequestBody UpdateCustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(customerId, request));
    }

    /**
     * GET /api/v1/customers
     * Search customers with pagination - maps to SOAP SearchCustomers
     */
    @GetMapping
    @Operation(summary = "Search customers with pagination", description = "Paginated search across customer accounts with optional filtering. Maps to SOAP SearchCustomers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful", content = @Content(schema = @Schema(implementation = SearchCustomersResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid filter parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<SearchCustomersResponse> searchCustomers(
            @Parameter(description = "Filter by email (partial match)")
            @RequestParam(required = false) String email,
            @Parameter(description = "Filter by customer status")
            @RequestParam(required = false) CustomerStatus status,
            @Parameter(description = "Filter by customer tier")
            @RequestParam(required = false) CustomerTier tier,
            @Parameter(description = "Page number (1-based)")
            @RequestParam(defaultValue = "1") int pageNumber,
            @Parameter(description = "Page size (records per page)")
            @RequestParam(defaultValue = "20") int pageSize) {
        
        SearchCustomersRequest request = SearchCustomersRequest.builder()
                .email(email)
                .status(status)
                .tier(tier)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
        
        return ResponseEntity.ok(customerService.searchCustomers(request));
    }
}

