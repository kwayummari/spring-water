
// src/main/java/tz/co/itrust/services/nida/soap/datacontract/ResponseBody.java
package tz.co.itrust.services.nida.soap.datacontract;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseBody", propOrder = {
    "cryptoInfo",
    "payload",
    "signature"
})
public class ResponseBody {

    @XmlElementRef(name = "CryptoInfo", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<ResponseCryptoInfo> cryptoInfo;
    @XmlElementRef(name = "Payload", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<String> payload;
    @XmlElementRef(name = "Signature", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<String> signature;

    public JAXBElement<ResponseCryptoInfo> getCryptoInfo() {
        return cryptoInfo;
    }

    public void setCryptoInfo(JAXBElement<ResponseCryptoInfo> value) {
        this.cryptoInfo = value;
    }

    public JAXBElement<String> getPayload() {
        return payload;
    }

    public void setPayload(JAXBElement<String> value) {
        this.payload = value;
    }

    public JAXBElement<String> getSignature() {
        return signature;
    }

    public void setSignature(JAXBElement<String> value) {
        this.signature = value;
    }
}