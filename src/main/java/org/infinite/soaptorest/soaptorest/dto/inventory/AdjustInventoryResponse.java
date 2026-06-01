package org.infinite.soaptorest.soaptorest.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;

/**
 * Response for adjusting inventory - maps to SOAP AdjustInventoryResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdjustInventoryResponse {
    private ResponseHeader header;
    private InventoryLevelDTO inventoryLevel;
}

