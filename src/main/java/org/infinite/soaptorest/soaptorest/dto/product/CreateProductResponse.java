package org.infinite.soaptorest.soaptorest.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;

/**
 * Response for creating a product - maps to SOAP CreateProductResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResponse {
    private ResponseHeader header;
    private ProductDTO product;
}

