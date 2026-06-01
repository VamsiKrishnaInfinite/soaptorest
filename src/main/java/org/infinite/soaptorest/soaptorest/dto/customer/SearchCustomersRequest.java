package org.infinite.soaptorest.soaptorest.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Search criteria for customers - maps to SOAP SearchCustomersRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCustomersRequest {
    private String email;
    private CustomerStatus status;
    private CustomerTier tier;
    private int pageNumber = 1;
    private int pageSize = 20;
    private String sortField = "createdAt";
    private String sortDir = "DESC";
}

