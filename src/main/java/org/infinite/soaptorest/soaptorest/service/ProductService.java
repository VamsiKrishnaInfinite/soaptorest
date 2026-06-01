package org.infinite.soaptorest.soaptorest.service;

import org.infinite.soaptorest.soaptorest.dto.product.*;
import org.infinite.soaptorest.soaptorest.util.ResponseHeaderUtil;
import org.infinite.soaptorest.soaptorest.exception.ResourceNotFoundException;
import org.infinite.soaptorest.soaptorest.dto.common.ListWrapper;
import org.infinite.soaptorest.soaptorest.dto.common.PageMeta;
import org.infinite.soaptorest.soaptorest.model.Product;
import org.infinite.soaptorest.soaptorest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Product Service - handles all product-related operations
 * Maps to SOAP CatalogPortType
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Create a new product - maps to SOAP CreateProduct
     */
    public CreateProductResponse createProduct(CreateProductRequest request) {
        ProductDTO product = ProductDTO.builder()
                .productId(UUID.randomUUID())
                .name(request.getName())
                .description(request.getDescription())
                .brand(request.getBrand())
                .productType(request.getProductType())
                .status(ProductStatus.DRAFT)
                .basePrice(request.getBasePrice())
                .salePrice(request.getSalePrice())
                .availableStock(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product mongoProduct = Product.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .productType(product.getProductType())
                .status(product.getStatus())
                .basePrice(product.getBasePrice())
                .salePrice(product.getSalePrice())
                .availableStock(product.getAvailableStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();

        productRepository.save(mongoProduct);

        return CreateProductResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .product(product)
                .build();
    }

    /**
     * Get product by ID - maps to SOAP GetProduct
     */
    public GetProductResponse getProduct(GetProductRequest request) {
        Product mongoProduct = productRepository.findByProductId(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", request.getProductId().toString()));

        ProductDTO product = ProductDTO.builder()
                .productId(mongoProduct.getProductId())
                .name(mongoProduct.getName())
                .description(mongoProduct.getDescription())
                .brand(mongoProduct.getBrand())
                .productType(mongoProduct.getProductType())
                .status(mongoProduct.getStatus())
                .basePrice(mongoProduct.getBasePrice())
                .salePrice(mongoProduct.getSalePrice())
                .availableStock(mongoProduct.getAvailableStock())
                .createdAt(mongoProduct.getCreatedAt())
                .updatedAt(mongoProduct.getUpdatedAt())
                .build();

        return GetProductResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .product(product)
                .build();
    }

    /**
     * Search products with pagination - maps to SOAP SearchProducts
     */
    public SearchProductsResponse searchProducts(SearchProductsRequest request) {
        List<Product> mongoProducts = productRepository.findAll();

        List<Product> results = mongoProducts.stream()
                .filter(p -> request.getKeyword() == null || p.getName().contains(request.getKeyword()))
                .filter(p -> request.getBrand() == null || p.getBrand().equals(request.getBrand()))
                .filter(p -> request.getProductType() == null || p.getProductType() == request.getProductType())
                .filter(p -> request.getMinPrice() == null || p.getBasePrice().compareTo(request.getMinPrice()) >= 0)
                .filter(p -> request.getMaxPrice() == null || p.getBasePrice().compareTo(request.getMaxPrice()) <= 0)
                .filter(p -> !request.isInStockOnly() || p.getAvailableStock() > 0)
                .toList();

        int totalRecords = results.size();
        int pageSize = request.getPageSize();
        int pageNumber = request.getPageNumber();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalRecords);

        List<Product> pageResults = results.subList(startIndex, Math.max(startIndex, endIndex));

        List<ProductDTO> products = pageResults.stream()
                .map(p -> ProductDTO.builder()
                        .productId(p.getProductId())
                        .name(p.getName())
                        .description(p.getDescription())
                        .brand(p.getBrand())
                        .productType(p.getProductType())
                        .status(p.getStatus())
                        .basePrice(p.getBasePrice())
                        .salePrice(p.getSalePrice())
                        .availableStock(p.getAvailableStock())
                        .createdAt(p.getCreatedAt())
                        .updatedAt(p.getUpdatedAt())
                        .build())
                .toList();

        return SearchProductsResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .products(new ListWrapper<>(products))
                .pageMeta(PageMeta.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .totalRecords(totalRecords)
                        .totalPages(totalPages)
                        .hasNext(pageNumber < totalPages)
                        .hasPrevious(pageNumber > 1)
                        .build())
                .build();
    }
}

