package org.infinite.soaptorest.soaptorest.exception;

/**
 * Exception for validation errors (mapping to SOAP validation faults)
 */
public class ValidationException extends ServiceException {
    public ValidationException(String message) {
        super("VALIDATION_ERROR", message, 400);
    }

    public ValidationException(String message, String field) {
        super("VALIDATION_ERROR", message, field, 400);
    }
}

