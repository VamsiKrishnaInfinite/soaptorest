package org.infinite.soaptorest.soaptorest.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;
import org.infinite.soaptorest.soaptorest.dto.common.PageMeta;
import org.infinite.soaptorest.soaptorest.dto.common.ListWrapper;

/**
 * Response for searching products - maps to SOAP SearchProductsResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductsResponse {
    private ResponseHeader header;
    private ListWrapper<ProductDTO> products;
    private PageMeta pageMeta;
}

