package org.infinite.soaptorest.soaptorest.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;

/**
 * Response for getting a product - maps to SOAP GetProductResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductResponse {
    private ResponseHeader header;
    private ProductDTO product;
}

