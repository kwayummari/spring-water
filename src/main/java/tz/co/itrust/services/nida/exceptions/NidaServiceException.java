package tz.co.itrust.services.nida.exceptions;

/**
 * Base exception for NIDA service errors
 */
public class NidaServiceException extends RuntimeException {
    public NidaServiceException(String message) {
        super(message);
    }
    
    public NidaServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}