package org.infinite.soaptorest.soaptorest.exception;

/**
 * Exception for resource not found errors (404)
 */
public class ResourceNotFoundException extends ServiceException {
    public ResourceNotFoundException(String resourceType, String resourceId) {
        super("RESOURCE_NOT_FOUND",
              String.format("%s with ID %s not found", resourceType, resourceId),
              404);
    }

    public ResourceNotFoundException(String message) {
        super("RESOURCE_NOT_FOUND", message, 404);
    }
}

