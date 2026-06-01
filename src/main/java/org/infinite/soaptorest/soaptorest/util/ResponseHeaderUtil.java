package org.infinite.soaptorest.soaptorest.util;

import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;
import org.infinite.soaptorest.soaptorest.dto.common.StatusCode;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Utility class for creating response headers
 */
public class ResponseHeaderUtil {
    public static ResponseHeader createSuccessHeader() {
        return ResponseHeader.builder()
                .requestId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .status(StatusCode.SUCCESS)
                .build();
    }

    public static ResponseHeader createFailureHeader(String errorCode, String errorMessage) {
        return ResponseHeader.builder()
                .requestId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .status(StatusCode.FAILURE)
                .build();
    }
}

