package org.infinite.soaptorest.soaptorest.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

/**
 * Request to create a product - maps to SOAP CreateProductRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @NotBlank(message = "Product name is required")
    @Schema(description = "Product name", example = "Wireless Headphones")
    private String name;

    @Schema(description = "Product description", example = "High-quality wireless headphones with noise cancellation")
    private String description;

    @Schema(description = "Product brand", example = "TechBrand")
    private String brand;

    @NotNull(message = "Product type is required")
    @Schema(description = "Product type", example = "PHYSICAL")
    private ProductType productType;

    @NotNull(message = "Base price is required")
    @DecimalMin("0.01")
    @Schema(description = "Base price of the product", example = "99.99")
    private BigDecimal basePrice;

    @Schema(description = "Sale price of the product", example = "79.99")
    private BigDecimal salePrice;

    @NotNull(message = "Category IDs are required")
    @Schema(description = "Array of category IDs", example = "[\"cat-001\", \"cat-002\"]")
    private String[] categoryIds;
}

