package org.infinite.soaptorest.soaptorest.service;

import org.infinite.soaptorest.soaptorest.dto.inventory.*;
import org.infinite.soaptorest.soaptorest.util.ResponseHeaderUtil;
import org.infinite.soaptorest.soaptorest.exception.ResourceNotFoundException;
import org.infinite.soaptorest.soaptorest.exception.InventoryException;
import org.infinite.soaptorest.soaptorest.dto.common.ListWrapper;
import org.infinite.soaptorest.soaptorest.model.InventoryLevel;
import org.infinite.soaptorest.soaptorest.repository.InventoryLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Inventory Service - handles all inventory-related operations
 * Maps to SOAP InventoryPortType
 */
@Service
public class InventoryService {

    @Autowired
    private InventoryLevelRepository inventoryLevelRepository;

    /**
     * Get inventory levels - maps to SOAP GetInventoryLevel
     */
    public GetInventoryLevelResponse getInventoryLevel(GetInventoryLevelRequest request) {
        InventoryLevel mongoLevel = inventoryLevelRepository.findByVariantIdAndWarehouseId(
                request.getVariantId(), request.getWarehouseId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventory level not found for variant: " + request.getVariantId(), ""));

        InventoryLevelDTO level = InventoryLevelDTO.builder()
                .variantId(mongoLevel.getVariantId())
                .warehouseId(mongoLevel.getWarehouseId())
                .quantity(mongoLevel.getQuantity())
                .reserved(mongoLevel.getReserved())
                .available(mongoLevel.getAvailable())
                .build();

        return GetInventoryLevelResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .inventoryLevels(new ListWrapper<>(List.of(level)))
                .build();
    }

    /**
     * Adjust inventory - maps to SOAP AdjustInventory
     */
    public AdjustInventoryResponse adjustInventory(AdjustInventoryRequest request) {
       Optional<InventoryLevel> existingLevel = inventoryLevelRepository.findByVariantIdAndWarehouseId(
                request.getVariantId(), request.getWarehouseId());

        InventoryLevel mongoLevel = existingLevel.orElse(InventoryLevel.builder()
                .variantId(request.getVariantId())
                .warehouseId(request.getWarehouseId())
                .quantity(0)
                .reserved(0)
                .available(0)
                .build());

        int newQuantity = mongoLevel.getQuantity() + request.getQuantityDelta();

        if (newQuantity < 0) {
            throw new InventoryException("INSUFFICIENT_STOCK", 
                    "Insufficient stock. Current: " + mongoLevel.getQuantity() + ", Delta: " + request.getQuantityDelta());
        }

        mongoLevel.setQuantity(newQuantity);
        mongoLevel.setAvailable(newQuantity - mongoLevel.getReserved());
        inventoryLevelRepository.save(mongoLevel);

        InventoryLevelDTO level = InventoryLevelDTO.builder()
                .variantId(mongoLevel.getVariantId())
                .warehouseId(mongoLevel.getWarehouseId())
                .quantity(mongoLevel.getQuantity())
                .reserved(mongoLevel.getReserved())
                .available(mongoLevel.getAvailable())
                .build();

        return AdjustInventoryResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .inventoryLevel(level)
                .build();
    }
}


