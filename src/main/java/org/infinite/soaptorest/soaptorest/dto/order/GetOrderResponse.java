package org.infinite.soaptorest.soaptorest.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;

/**
 * Response for getting an order - maps to SOAP GetOrderResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderResponse {
    private ResponseHeader header;
    private OrderDTO order;
}

