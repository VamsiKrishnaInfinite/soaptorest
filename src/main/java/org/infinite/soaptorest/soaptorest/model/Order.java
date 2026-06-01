package org.infinite.soaptorest.soaptorest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.infinite.soaptorest.soaptorest.dto.order.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Order MongoDB Document
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
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

