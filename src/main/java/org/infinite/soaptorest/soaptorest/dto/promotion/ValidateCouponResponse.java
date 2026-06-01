package org.infinite.soaptorest.soaptorest.dto.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;
import java.math.BigDecimal;

/**
 * Response for validating a coupon - maps to SOAP ValidateCouponResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateCouponResponse {
    private ResponseHeader header;
    private boolean isValid;
    private String couponCode;
    private BigDecimal discountAmount;
    private String discountType;
}

