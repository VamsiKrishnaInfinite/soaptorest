package org.infinite.soaptorest.soaptorest.service;

import org.infinite.soaptorest.soaptorest.dto.promotion.*;
import org.infinite.soaptorest.soaptorest.util.ResponseHeaderUtil;
import org.infinite.soaptorest.soaptorest.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Promotion Service - handles coupon and promotion validation
 * Maps to SOAP PromotionPortType
 */
@Service
public class PromotionService {

    /**
     * Validate coupon - maps to SOAP ValidateCoupon
     */
    public ValidateCouponResponse validateCoupon(ValidateCouponRequest request) {
        // Mock coupon validation
        boolean isValid = validateCouponCode(request.getCouponCode());

        if (!isValid) {
            throw new BusinessException("INVALID_COUPON", "Coupon code is not valid or expired");
        }

        BigDecimal discountAmount = calculateDiscount(request.getCouponCode(), request.getCartTotal());

        return ValidateCouponResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .isValid(true)
                .couponCode(request.getCouponCode())
                .discountAmount(discountAmount)
                .discountType("PERCENTAGE")
                .build();
    }

    private boolean validateCouponCode(String code) {
        // Mock validation - accept codes starting with COUPON
        return code != null && code.startsWith("COUPON");
    }

    private BigDecimal calculateDiscount(String code, BigDecimal cartTotal) {
        // Mock discount calculation - 10% discount
        if (cartTotal == null) {
            return BigDecimal.ZERO;
        }
        return cartTotal.multiply(BigDecimal.valueOf(0.10));
    }
}

