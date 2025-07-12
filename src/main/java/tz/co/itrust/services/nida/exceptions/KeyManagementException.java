package tz.co.itrust.services.nida.exceptions;

/**
 * Exception for key management errors
 */
public class KeyManagementException extends NidaServiceException {
    public KeyManagementException(String message) {
        super(message);
    }
    
    public KeyManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}