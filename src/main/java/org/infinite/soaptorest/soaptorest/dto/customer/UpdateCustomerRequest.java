package org.infinite.soaptorest.soaptorest.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request to update a customer - maps to SOAP UpdateCustomerRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private CustomerStatus status;
}

