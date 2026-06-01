package org.infinite.soaptorest.soaptorest.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Order DTO - maps to SOAP OrderType
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private UUID orderId;
    private String orderNumber;
    private UUID customerId;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private BigDecimal tax;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

