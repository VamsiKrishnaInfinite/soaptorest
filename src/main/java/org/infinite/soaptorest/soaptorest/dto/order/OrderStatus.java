package org.infinite.soaptorest.soaptorest.dto.order;

/**
 * Order status enumeration
 */
public enum OrderStatus {
    DRAFT, PENDING_PAYMENT, PAYMENT_RECEIVED, PROCESSING, PARTIALLY_SHIPPED, SHIPPED, DELIVERED, COMPLETED, CANCELLED, REFUNDED, ON_HOLD
}

