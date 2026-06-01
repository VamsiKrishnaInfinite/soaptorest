package org.infinite.soaptorest.soaptorest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.infinite.soaptorest.soaptorest.model.InventoryLevel;
import java.util.Optional;
import java.util.UUID;

/**
 * MongoDB Repository for InventoryLevel
 */
@Repository
public interface InventoryLevelRepository extends MongoRepository<InventoryLevel, String> {
    Optional<InventoryLevel> findByVariantIdAndWarehouseId(UUID variantId, UUID warehouseId);
}

