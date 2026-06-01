package org.infinite.soaptorest.soaptorest.dto.promotion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * Request to validate a coupon - maps to SOAP ValidateCouponRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateCouponRequest {
    @NotBlank(message = "Coupon code is required")
    @Schema(description = "Coupon/Promotion code to validate", example = "COUPON10")
    private String couponCode;

    @Schema(description = "Total cart amount for discount calculation", example = "150.00")
    private BigDecimal cartTotal;
}

