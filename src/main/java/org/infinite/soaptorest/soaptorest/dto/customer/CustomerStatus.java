package org.infinite.soaptorest.soaptorest.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * Customer status enumeration
 */
public enum CustomerStatus {
    ACTIVE, INACTIVE, SUSPENDED, PENDING_VERIFICATION, DELETED
}

