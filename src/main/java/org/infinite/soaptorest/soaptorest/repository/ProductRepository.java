package org.infinite.soaptorest.soaptorest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.infinite.soaptorest.soaptorest.model.Product;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

/**
 * MongoDB Repository for Product
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByProductId(UUID productId);
    List<Product> findByNameContaining(String name);
    List<Product> findByBrand(String brand);
}

