package org.infinite.soaptorest.soaptorest.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Generic list wrapper for paginated responses
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListWrapper<T> {
    private List<T> items;
}

