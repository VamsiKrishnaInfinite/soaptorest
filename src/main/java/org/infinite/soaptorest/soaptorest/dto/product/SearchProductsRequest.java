package org.infinite.soaptorest.soaptorest.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * Search criteria for products - maps to SOAP SearchProductsRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductsRequest {
    private String keyword;
    private String categoryId;
    private String brand;
    private ProductType productType;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private boolean inStockOnly;
    private int pageNumber = 1;
    private int pageSize = 20;
    private String sortField = "name";
    private String sortDir = "ASC";
}

