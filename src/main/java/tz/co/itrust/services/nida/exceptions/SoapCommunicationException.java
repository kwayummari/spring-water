package tz.co.itrust.services.nida.exceptions;

/**
 * Exception for SOAP communication errors
 */
public class SoapCommunicationException extends NidaServiceException {
    public SoapCommunicationException(String message) {
        super(message);
    }
    
    public SoapCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}