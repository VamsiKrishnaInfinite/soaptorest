package org.infinite.soaptorest.soaptorest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.infinite.soaptorest.soaptorest.model.Customer;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

/**
 * MongoDB Repository for Customer
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findByCustomerId(UUID customerId);
    Optional<Customer> findByEmail(String email);
    List<Customer> findByEmailContaining(String email);
}

