package org.infinite.soaptorest.soaptorest.exception;

/**
 * Exception for payment-related errors
 */
public class PaymentException extends ServiceException {
    public PaymentException(String errorCode, String message) {
        super(errorCode, message, 402);
    }
}

