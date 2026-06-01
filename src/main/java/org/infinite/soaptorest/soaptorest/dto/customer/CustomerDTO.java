package org.infinite.soaptorest.soaptorest.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Customer DTO - maps to SOAP CustomerType
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private UUID customerId;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private CustomerStatus status;
    private CustomerTier tier;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

