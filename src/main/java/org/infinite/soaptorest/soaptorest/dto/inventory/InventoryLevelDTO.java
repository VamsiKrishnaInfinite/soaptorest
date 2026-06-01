package org.infinite.soaptorest.soaptorest.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * Inventory level DTO - maps to SOAP InventoryLevel
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryLevelDTO {
    private UUID variantId;
    private UUID warehouseId;
    private int quantity;
    private int reserved;
    private int available;
}

