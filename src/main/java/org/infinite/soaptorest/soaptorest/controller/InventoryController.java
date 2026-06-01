package org.infinite.soaptorest.soaptorest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.infinite.soaptorest.soaptorest.dto.inventory.*;
import org.infinite.soaptorest.soaptorest.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

/**
 * Inventory REST Controller
 * Maps SOAP InventoryPortType operations to REST endpoints
 */
@RestController
@RequestMapping("/api/v1/inventory")
@Validated
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * GET /api/v1/inventory
     * Get inventory level - maps to SOAP GetInventoryLevel
     */
    @GetMapping
    @Operation(summary = "Get inventory level", description = "Returns stock levels for a variant across one or all warehouses. Maps to SOAP GetInventoryLevel.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory levels retrieved", content = @Content(schema = @Schema(implementation = GetInventoryLevelResponse.class))),
            @ApiResponse(responseCode = "404", description = "Inventory level not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GetInventoryLevelResponse> getInventoryLevel(
            @Parameter(description = "Product variant ID (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440004")
            @RequestParam UUID variantId,
            @Parameter(description = "Warehouse ID (UUID) - optional", example = "550e8400-e29b-41d4-a716-446655440005")
            @RequestParam(required = false) UUID warehouseId) {
        
        GetInventoryLevelRequest request = GetInventoryLevelRequest.builder()
                .variantId(variantId)
                .warehouseId(warehouseId)
                .build();
        
        return ResponseEntity.ok(inventoryService.getInventoryLevel(request));
    }

    /**
     * POST /api/v1/inventory/adjustments
     * Adjust inventory - maps to SOAP AdjustInventory
     */
    @PostMapping("/adjustments")
    @Operation(summary = "Adjust inventory stock", description = "Applies a signed quantity delta to a variant's stock at a given warehouse. Maps to SOAP AdjustInventory.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory adjusted successfully", content = @Content(schema = @Schema(implementation = AdjustInventoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or insufficient stock"),
            @ApiResponse(responseCode = "422", description = "Inventory error (e.g., negative stock)"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<AdjustInventoryResponse> adjustInventory(@Valid @RequestBody AdjustInventoryRequest request) {
        return ResponseEntity.ok(inventoryService.adjustInventory(request));
    }
}

