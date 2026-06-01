package org.infinite.soaptorest.soaptorest.exception;

/**
 * Exception for inventory/stock-related errors
 */
public class InventoryException extends ServiceException {
    public InventoryException(String message) {
        super("INVENTORY_ERROR", message, 422);
    }

    public InventoryException(String errorCode, String message) {
        super(errorCode, message, 422);
    }
}

