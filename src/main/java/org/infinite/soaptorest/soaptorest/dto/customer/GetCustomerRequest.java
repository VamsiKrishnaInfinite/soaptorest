package org.infinite.soaptorest.soaptorest.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * Request to get a customer - maps to SOAP GetCustomerRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerRequest {
    private UUID customerId;
}

