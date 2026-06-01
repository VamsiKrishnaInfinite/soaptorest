package org.infinite.soaptorest.soaptorest.handler;

import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;
import org.infinite.soaptorest.soaptorest.dto.common.ServiceError;
import org.infinite.soaptorest.soaptorest.dto.common.StatusCode;
import org.infinite.soaptorest.soaptorest.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Global exception handler for all service exceptions
 * Maps exceptions to SOAP fault responses
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle ServiceException (generic)
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .header(createErrorHeader(ex.getErrorCode(), ex.getMessage()))
                .build();
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

    /**
     * Handle ResourceNotFoundException (404)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .header(createErrorHeader(ex.getErrorCode(), ex.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Handle ValidationException (400)
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .header(createErrorHeader(ex.getErrorCode(), ex.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handle BusinessException (422)
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .header(createErrorHeader(ex.getErrorCode(), ex.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    /**
     * Handle InventoryException (422)
     */
    @ExceptionHandler(InventoryException.class)
    public ResponseEntity<ErrorResponse> handleInventoryException(InventoryException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .header(createErrorHeader(ex.getErrorCode(), ex.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    /**
     * Handle PaymentException (402)
     */
    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ErrorResponse> handlePaymentException(PaymentException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .header(createErrorHeader(ex.getErrorCode(), ex.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(response);
    }

    /**
     * Handle validation errors from @Valid annotation
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ServiceError> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.add(ServiceError.builder()
                    .errorCode("VALIDATION_ERROR")
                    .errorMessage(message)
                    .field(fieldName)
                    .timestamp(LocalDateTime.now())
                    .build());
        });

        ErrorResponse response = ErrorResponse.builder()
                .header(ResponseHeader.builder()
                        .requestId(UUID.randomUUID())
                        .timestamp(LocalDateTime.now())
                        .status(StatusCode.FAILURE)
                        .errors(errors)
                        .build())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handle generic exceptions
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse response = ErrorResponse.builder()
                .header(createErrorHeader("INTERNAL_ERROR", ex.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private ResponseHeader createErrorHeader(String errorCode, String errorMessage) {
        return ResponseHeader.builder()
                .requestId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .status(StatusCode.FAILURE)
                .errors(List.of(ServiceError.builder()
                        .errorCode(errorCode)
                        .errorMessage(errorMessage)
                        .timestamp(LocalDateTime.now())
                        .build()))
                .build();
    }
}

