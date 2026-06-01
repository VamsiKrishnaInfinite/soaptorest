package org.infinite.soaptorest.soaptorest.dto.inventory;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * Request to get inventory level - maps to SOAP GetInventoryLevelRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetInventoryLevelRequest {
    @NotNull(message = "Variant ID is required")
    @Schema(description = "Product variant ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440004")
    private UUID variantId;

    @Schema(description = "Warehouse ID (UUID) - optional, if not provided all warehouses will be queried", example = "550e8400-e29b-41d4-a716-446655440005")
    private UUID warehouseId;
}

