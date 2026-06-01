package org.infinite.soaptorest.soaptorest.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Product DTO - maps to SOAP ProductType
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private UUID productId;
    private String name;
    private String description;
    private String brand;
    private ProductType productType;
    private ProductStatus status;
    private BigDecimal basePrice;
    private BigDecimal salePrice;
    private int availableStock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

