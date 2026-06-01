package org.infinite.soaptorest.soaptorest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.infinite.soaptorest.soaptorest.dto.order.*;
import org.infinite.soaptorest.soaptorest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

/**
 * Order REST Controller
 * Maps SOAP OrderPortType operations to REST endpoints
 */
@RestController
@RequestMapping("/api/v1/orders")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * GET /api/v1/orders
     * Get all orders
     */
    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieves all orders in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    /**
     * POST /api/v1/orders
     * Place a new order - maps to SOAP PlaceOrder
     */
    @PostMapping
    @Operation(summary = "Place a new order", description = "Places an order from a cart. Triggers payment capture and inventory reservation. Maps to SOAP PlaceOrder.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order placed successfully", content = @Content(schema = @Schema(implementation = PlaceOrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or empty cart"),
            @ApiResponse(responseCode = "422", description = "Business logic error (e.g., out of stock)"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PlaceOrderResponse> placeOrder(@Valid @RequestBody PlaceOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(request));
    }

    /**
     * GET /api/v1/orders/{orderId}
     * Get order by ID - maps to SOAP GetOrder
     */
    @GetMapping("/{orderId}")
    @Operation(summary = "Get order by ID", description = "Retrieves a single order by ID or order number. Maps to SOAP GetOrder.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found", content = @Content(schema = @Schema(implementation = GetOrderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GetOrderResponse> getOrder(
            @Parameter(description = "Order ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440006")
            @PathVariable UUID orderId) {
        GetOrderRequest request = GetOrderRequest.builder().orderId(orderId).build();
        return ResponseEntity.ok(orderService.getOrder(request));
    }

    /**
     * PATCH /api/v1/orders/{orderId}/status
     * Update order status - maps to SOAP UpdateOrderStatus
     */
    @PatchMapping("/{orderId}/status")
    @Operation(summary = "Update order status", description = "Transitions an order to a new status; optionally notifies the customer. Maps to SOAP UpdateOrderStatus.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order status updated", content = @Content(schema = @Schema(implementation = UpdateOrderStatusResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid status transition"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UpdateOrderStatusResponse> updateOrderStatus(
            @Parameter(description = "Order ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440006")
            @PathVariable UUID orderId,
            @Valid @RequestBody UpdateOrderStatusRequest request) {
        request.setOrderId(orderId);
        return ResponseEntity.ok(orderService.updateOrderStatus(request));
    }
}

