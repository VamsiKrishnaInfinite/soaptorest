package org.infinite.soaptorest.soaptorest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.infinite.soaptorest.soaptorest.model.Order;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

/**
 * MongoDB Repository for Order
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    Optional<Order> findByOrderId(UUID orderId);
    List<Order> findByCustomerId(UUID customerId);
}

