package org.infinite.soaptorest.soaptorest.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;

/**
 * Response for getting a customer - maps to SOAP GetCustomerResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerResponse {
    private ResponseHeader header;
    private CustomerDTO customer;
}

