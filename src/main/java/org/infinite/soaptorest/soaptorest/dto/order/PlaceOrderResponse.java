package org.infinite.soaptorest.soaptorest.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;

/**
 * Response for placing an order - maps to SOAP PlaceOrderResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderResponse {
    private ResponseHeader header;
    private OrderDTO order;
}

