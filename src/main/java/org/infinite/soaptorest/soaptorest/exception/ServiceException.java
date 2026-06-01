package org.infinite.soaptorest.soaptorest.exception;

/**
 * Base exception for all service-level errors.
 * Maps to SOAP ServiceFault
 */
public class ServiceException extends RuntimeException {
    private String errorCode;
    private String field;
    private int httpStatus;

    public ServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = 500;
    }

    public ServiceException(String errorCode, String message, String field) {
        super(message);
        this.errorCode = errorCode;
        this.field = field;
        this.httpStatus = 400;
    }

    public ServiceException(String errorCode, String message, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public ServiceException(String errorCode, String message, String field, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.field = field;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getField() {
        return field;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}

