package org.infinite.soaptorest.soaptorest.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;
import org.infinite.soaptorest.soaptorest.dto.common.ListWrapper;

/**
 * Response for getting inventory level - maps to SOAP GetInventoryLevelResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetInventoryLevelResponse {
    private ResponseHeader header;
    private ListWrapper<InventoryLevelDTO> inventoryLevels;
}

