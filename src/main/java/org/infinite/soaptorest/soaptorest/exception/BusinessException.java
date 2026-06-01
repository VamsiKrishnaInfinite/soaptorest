package org.infinite.soaptorest.soaptorest.exception;

/**
 * Exception for business logic violations (mapping to SOAP business faults)
 */
public class BusinessException extends ServiceException {
    public BusinessException(String errorCode, String message) {
        super(errorCode, message, 422);
    }

    public BusinessException(String errorCode, String message, String details) {
        super(errorCode, message + ": " + details, 422);
    }
}

