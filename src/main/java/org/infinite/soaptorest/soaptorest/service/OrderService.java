package org.infinite.soaptorest.soaptorest.service;

import org.infinite.soaptorest.soaptorest.dto.order.*;
import org.infinite.soaptorest.soaptorest.util.ResponseHeaderUtil;
import org.infinite.soaptorest.soaptorest.exception.ResourceNotFoundException;
import org.infinite.soaptorest.soaptorest.exception.BusinessException;
import org.infinite.soaptorest.soaptorest.model.Order;
import org.infinite.soaptorest.soaptorest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Order Service - handles all order-related operations
 * Maps to SOAP OrderPortType
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private static final AtomicInteger orderCounter = new AtomicInteger(1000);

    /**
     * Place a new order - maps to SOAP PlaceOrder
     */
    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {
        if (request.getItems() == null || request.getItems().length == 0) {
            throw new BusinessException("EMPTY_CART", "Cannot place order with no items");
        }

        OrderDTO order = OrderDTO.builder()
                .orderId(UUID.randomUUID())
                .orderNumber("ORD-" + (orderCounter.incrementAndGet()))
                .customerId(request.getCustomerId())
                .status(OrderStatus.PENDING_PAYMENT)
                .totalAmount(BigDecimal.ZERO)
                .discount(BigDecimal.ZERO)
                .tax(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Order mongoOrder = Order.builder()
                .orderId(order.getOrderId())
                .orderNumber(order.getOrderNumber())
                .customerId(order.getCustomerId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .discount(order.getDiscount())
                .tax(order.getTax())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();

        orderRepository.save(mongoOrder);

        return PlaceOrderResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .order(order)
                .build();
    }

    /**
     * Get order by ID - maps to SOAP GetOrder
     */
    public GetOrderResponse getOrder(GetOrderRequest request) {
        Order mongoOrder = orderRepository.findByOrderId(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order", request.getOrderId().toString()));

        OrderDTO order = OrderDTO.builder()
                .orderId(mongoOrder.getOrderId())
                .orderNumber(mongoOrder.getOrderNumber())
                .customerId(mongoOrder.getCustomerId())
                .status(mongoOrder.getStatus())
                .totalAmount(mongoOrder.getTotalAmount())
                .discount(mongoOrder.getDiscount())
                .tax(mongoOrder.getTax())
                .createdAt(mongoOrder.getCreatedAt())
                .updatedAt(mongoOrder.getUpdatedAt())
                .build();

        return GetOrderResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .order(order)
                .build();
    }

    /**
     * Update order status - maps to SOAP UpdateOrderStatus
     */
    public UpdateOrderStatusResponse updateOrderStatus(UpdateOrderStatusRequest request) {
        Order mongoOrder = orderRepository.findByOrderId(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order", request.getOrderId().toString()));

        mongoOrder.setStatus(request.getStatus());
        mongoOrder.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(mongoOrder);

        OrderDTO order = OrderDTO.builder()
                .orderId(mongoOrder.getOrderId())
                .orderNumber(mongoOrder.getOrderNumber())
                .customerId(mongoOrder.getCustomerId())
                .status(mongoOrder.getStatus())
                .totalAmount(mongoOrder.getTotalAmount())
                .discount(mongoOrder.getDiscount())
                .tax(mongoOrder.getTax())
                .createdAt(mongoOrder.getCreatedAt())
                .updatedAt(mongoOrder.getUpdatedAt())
                .build();

        return UpdateOrderStatusResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .order(order)
                .build();
    }

    /**
     * Get all orders
     */
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(o -> OrderDTO.builder()
                        .orderId(o.getOrderId())
                        .orderNumber(o.getOrderNumber())
                        .customerId(o.getCustomerId())
                        .status(o.getStatus())
                        .totalAmount(o.getTotalAmount())
                        .discount(o.getDiscount())
                        .tax(o.getTax())
                        .createdAt(o.getCreatedAt())
                        .updatedAt(o.getUpdatedAt())
                        .build())
                .toList();
    }
}

