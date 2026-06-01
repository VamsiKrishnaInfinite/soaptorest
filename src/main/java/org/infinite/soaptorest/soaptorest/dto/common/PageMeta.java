package org.infinite.soaptorest.soaptorest.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Pagination metadata for list responses
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageMeta {
    private int pageNumber;
    private int pageSize;
    private long totalRecords;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
}

