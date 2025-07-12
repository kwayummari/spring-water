package tz.co.itrust.services.nida.soap.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * SOAP payload for biometric verification
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "Payload")
public class BiometricPayload {
    
    @XmlElement(name = "NIN")
    private String nin;
    
    @XmlElement(name = "FINGERCODE")
    private String fingerCode;
    
    @XmlElement(name = "FINGERIMAGE")
    private String fingerprintImage;
    
    public BiometricPayload(String nin) {
        this.nin = nin;
    }
    
    public BiometricPayload(String nin, String fingerCode, String fingerprintImage) {
        this.nin = nin;
        this.fingerCode = fingerCode;
        this.fingerprintImage = fingerprintImage;
    }
}