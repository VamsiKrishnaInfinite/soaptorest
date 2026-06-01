package org.infinite.soaptorest.soaptorest.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Represents a service error - maps to SOAP ServiceError
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceError {
    private String errorCode;
    private String errorMessage;
    private String field;
    private LocalDateTime timestamp;
}

