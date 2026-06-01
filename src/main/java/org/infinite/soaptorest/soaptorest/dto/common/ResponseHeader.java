package org.infinite.soaptorest.soaptorest.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Response header included in all responses - maps to SOAP ResponseHeader
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseHeader {
    private UUID requestId;
    private LocalDateTime timestamp;
    private StatusCode status;
    private List<ServiceError> errors;
}

