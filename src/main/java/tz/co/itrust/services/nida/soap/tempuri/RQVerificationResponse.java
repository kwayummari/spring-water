package tz.co.itrust.services.nida.soap.tempuri;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.*;
import tz.co.itrust.services.nida.soap.datacontract.CIGResponse;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"rqVerificationResult"})
@XmlRootElement(name = "RQVerificationResponse", namespace = "http://tempuri.org/")
public class RQVerificationResponse {

    @XmlElementRef(name = "RQVerificationResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<CIGResponse> rqVerificationResult;

    public JAXBElement<CIGResponse> getRQVerificationResult() {
        return rqVerificationResult;
    }

    public void setRQVerificationResult(JAXBElement<CIGResponse> value) {
        this.rqVerificationResult = value;
    }
}