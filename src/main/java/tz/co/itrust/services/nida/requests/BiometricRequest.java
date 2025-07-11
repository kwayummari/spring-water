package tz.co.itrust.services.nida.requests;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Payload")
public class BiometricRequest {
    @XmlElement(name = "NIN")
    private String nin;

    @XmlElement(name = "FINGERIMAGE")
    private String fingerprintImage;

    @XmlElement(name = "FINGERCODE")
    private String fingerCode;

    public void setNinValue(String nin) {
        this.nin = nin;
    }

    public BiometricRequest(String nin) {
        this.nin = nin;
    }

    public BiometricRequest(String nin, String fingerCode, String fingerprintImage) {
        this.nin = nin;
        this.fingerCode = fingerCode;
        this.fingerprintImage = fingerprintImage;
    }

    public BiometricRequest() {    }

    public String getFingerprintImageValue() {
        return fingerprintImage;
    }

    public void setFingerprintImageValue(String fingerprintImage) {
        this.fingerprintImage = fingerprintImage;
    }

    public String getFingerCodeValue() {
        return fingerCode;
    }

    public void setFingerCodeValue(String fingerCode) {
        this.fingerCode = fingerCode;
    }

    public String getNinValue() {
        return nin;
    }
}
