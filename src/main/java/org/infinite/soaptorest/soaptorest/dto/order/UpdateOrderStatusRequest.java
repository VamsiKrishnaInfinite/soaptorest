package org.infinite.soaptorest.soaptorest.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * Request to update order status - maps to SOAP UpdateOrderStatusRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusRequest {
    private UUID orderId;
    private OrderStatus status;
    private String notificationMessage;
}

