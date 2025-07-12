package tz.co.itrust.services.nida.exceptions;

/**
 * Exception for cryptography-related errors
 */
public class CryptographyException extends NidaServiceException {
    public CryptographyException(String message) {
        super(message);
    }
    
    public CryptographyException(String message, Throwable cause) {
        super(message, cause);
    }
}