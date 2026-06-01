package org.infinite.soaptorest.soaptorest.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;
import org.infinite.soaptorest.soaptorest.dto.common.PageMeta;
import org.infinite.soaptorest.soaptorest.dto.common.ListWrapper;

/**
 * Response for searching customers - maps to SOAP SearchCustomersResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCustomersResponse {
    private ResponseHeader header;
    private ListWrapper<CustomerDTO> customers;
    private PageMeta pageMeta;
}

