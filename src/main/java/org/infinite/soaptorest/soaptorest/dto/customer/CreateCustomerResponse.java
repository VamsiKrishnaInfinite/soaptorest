package org.infinite.soaptorest.soaptorest.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;

/**
 * Response for creating a customer - maps to SOAP CreateCustomerResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerResponse {
    private ResponseHeader header;
    private CustomerDTO customer;
}

