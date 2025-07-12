package tz.co.itrust.services.nida.soap.tempuri;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.*;
import tz.co.itrust.services.nida.soap.datacontract.CIGResponse;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"biometricVerificationResult"})
@XmlRootElement(name = "BiometricVerificationResponse")
public class VerificationResponse {

    @XmlElementRef(name = "BiometricVerificationResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<CIGResponse> biometricVerificationResult;

    public JAXBElement<CIGResponse> getBiometricVerificationResult() {
        return biometricVerificationResult;
    }

    public void setBiometricVerificationResult(JAXBElement<CIGResponse> value) {
        this.biometricVerificationResult = value;
    }
}