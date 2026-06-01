package org.infinite.soaptorest.soaptorest.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * Request to get a product - maps to SOAP GetProductRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductRequest {
    private UUID productId;
    private String slug;
    private boolean includeReviews;
}

