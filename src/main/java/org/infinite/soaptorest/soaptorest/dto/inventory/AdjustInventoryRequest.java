package org.infinite.soaptorest.soaptorest.dto.inventory;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * Request to adjust inventory - maps to SOAP AdjustInventoryRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdjustInventoryRequest {
    @NotNull(message = "Variant ID is required")
    @Schema(description = "Product variant ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440004")
    private UUID variantId;

    @NotNull(message = "Warehouse ID is required")
    @Schema(description = "Warehouse ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440005")
    private UUID warehouseId;

    @NotNull(message = "Quantity delta is required")
    @Schema(description = "Quantity adjustment (positive to add, negative to reduce)", example = "100")
    private int quantityDelta;

    @Schema(description = "Reason for adjustment", example = "Received from supplier")
    private String reason;
}

