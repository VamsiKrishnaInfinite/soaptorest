package org.infinite.soaptorest.soaptorest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.infinite.soaptorest.soaptorest.dto.customer.CustomerStatus;
import org.infinite.soaptorest.soaptorest.dto.customer.CustomerTier;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Customer MongoDB Document
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
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

