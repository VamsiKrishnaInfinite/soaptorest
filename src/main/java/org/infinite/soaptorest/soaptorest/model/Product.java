package org.infinite.soaptorest.soaptorest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.infinite.soaptorest.soaptorest.dto.product.ProductStatus;
import org.infinite.soaptorest.soaptorest.dto.product.ProductType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Product MongoDB Document
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
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

