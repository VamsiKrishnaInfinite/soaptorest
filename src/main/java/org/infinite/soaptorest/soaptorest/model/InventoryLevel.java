package org.infinite.soaptorest.soaptorest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

/**
 * Inventory Level MongoDB Document
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "inventory_levels")
public class InventoryLevel {
    @Id
    private String id;
    private UUID variantId;
    private UUID warehouseId;
    private int quantity;
    private int reserved;
    private int available;
}

