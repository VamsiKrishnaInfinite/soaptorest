package org.infinite.soaptorest.soaptorest.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * Request to get an order - maps to SOAP GetOrderRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderRequest {
    private UUID orderId;
}

