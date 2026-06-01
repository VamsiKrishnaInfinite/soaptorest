package org.infinite.soaptorest.soaptorest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.infinite.soaptorest.soaptorest.dto.promotion.*;
import org.infinite.soaptorest.soaptorest.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



/**
 * Promotion REST Controller
 * Maps SOAP PromotionPortType operations to REST endpoints
 */
@RestController
@RequestMapping("/api/v1/promotions")
@Validated
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    /**
     * POST /api/v1/promotions/coupons/validate
     * Validate coupon - maps to SOAP ValidateCoupon
     */
    @PostMapping("/coupons/validate")
    @Operation(summary = "Validate a coupon code", description = "Validates a coupon code against a customer's cart and computes the discount. Maps to SOAP ValidateCoupon.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coupon validation result", content = @Content(schema = @Schema(implementation = ValidateCouponResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "422", description = "Coupon is invalid or expired"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ValidateCouponResponse> validateCoupon(@Valid @RequestBody ValidateCouponRequest request) {
        return ResponseEntity.ok(promotionService.validateCoupon(request));
    }
}

