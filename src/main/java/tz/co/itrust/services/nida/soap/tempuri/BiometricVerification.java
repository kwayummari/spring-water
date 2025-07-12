package tz.co.itrust.services.nida.soap.tempuri;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.*;
import tz.co.itrust.services.nida.soap.datacontract.CIGRequest;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"iRequest"})
@XmlRootElement(name = "BiometricVerification")
public class BiometricVerification {

    @XmlElementRef(name = "iRequest", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<CIGRequest> iRequest;

    public JAXBElement<CIGRequest> getIRequest() {
        return iRequest;
    }

    public void setIRequest(JAXBElement<CIGRequest> value) {
        this.iRequest = value;
    }
}