package org.infinite.soaptorest.soaptorest.controller;

import org.infinite.soaptorest.soaptorest.dto.product.*;
import org.infinite.soaptorest.soaptorest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Product REST Controller
 * Maps SOAP CatalogPortType operations to REST endpoints
 */
@RestController
@RequestMapping("/api/v1/products")
@Validated
@Tag(name = "Products", description = "Product catalog operations - SOAP CreateProduct, GetProduct, SearchProducts")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * POST /api/v1/products
     * Create a new product - maps to SOAP CreateProduct
     */
    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product with variants and pricing. Maps to SOAP CreateProduct.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully", content = @Content(schema = @Schema(implementation = CreateProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CreateProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));
    }

    /**
     * GET /api/v1/products/{productId}
     * Get product by ID - maps to SOAP GetProduct
     */
    @GetMapping("/{productId}")
    @Operation(summary = "Get product by ID", description = "Retrieves a product by ID or URL slug. Maps to SOAP GetProduct.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found", content = @Content(schema = @Schema(implementation = GetProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GetProductResponse> getProduct(
            @Parameter(description = "Product ID (UUID)", example = "550e8400-e29b-41d4-a716-446655440003")
            @PathVariable UUID productId) {
        GetProductRequest request = GetProductRequest.builder()
                .productId(productId)
                .includeReviews(false)
                .build();
        return ResponseEntity.ok(productService.getProduct(request));
    }

    /**
     * GET /api/v1/products
     * Search products with pagination - maps to SOAP SearchProducts
     */
    @GetMapping
    @Operation(summary = "Search products with filters and pagination", description = "Full-text and faceted product search with pagination. Maps to SOAP SearchProducts.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful", content = @Content(schema = @Schema(implementation = SearchProductsResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid filter parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<SearchProductsResponse> searchProducts(
            @Parameter(description = "Search keyword in product name/description")
            @RequestParam(required = false) String keyword,
            @Parameter(description = "Filter by category ID")
            @RequestParam(required = false) String categoryId,
            @Parameter(description = "Filter by brand")
            @RequestParam(required = false) String brand,
            @Parameter(description = "Filter by product type")
            @RequestParam(required = false) ProductType productType,
            @Parameter(description = "Minimum price filter")
            @RequestParam(required = false) BigDecimal minPrice,
            @Parameter(description = "Maximum price filter")
            @RequestParam(required = false) BigDecimal maxPrice,
            @Parameter(description = "Return only in-stock products")
            @RequestParam(defaultValue = "false") boolean inStockOnly,
            @Parameter(description = "Page number (1-based)")
            @RequestParam(defaultValue = "1") int pageNumber,
            @Parameter(description = "Page size (records per page)")
            @RequestParam(defaultValue = "20") int pageSize) {

        SearchProductsRequest request = SearchProductsRequest.builder()
                .keyword(keyword)
                .categoryId(categoryId)
                .brand(brand)
                .productType(productType)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .inStockOnly(inStockOnly)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();

        return ResponseEntity.ok(productService.searchProducts(request));
    }
}

