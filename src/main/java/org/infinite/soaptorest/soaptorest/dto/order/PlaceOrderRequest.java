package org.infinite.soaptorest.soaptorest.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

/**
 * Request to place an order - maps to SOAP PlaceOrderRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {
    @NotNull(message = "Customer ID is required")
    @Schema(description = "Customer ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440001")
    private UUID customerId;

    @NotNull(message = "Order items are required")
    @Schema(description = "Array of order items")
    private OrderItem[] items;

    @Schema(description = "Coupon code to apply discount", example = "COUPON10")
    private String couponCode;

    @Schema(description = "Shipping method", example = "STANDARD")
    private String shippingMethod;
}

