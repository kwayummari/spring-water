package tz.co.itrust.services.nida.soap.datacontract;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestCryptoInfo", propOrder = {
    "encryptedCryptoIV",
    "encryptedCryptoKey"
})
public class RequestCryptoInfo {

    @XmlElementRef(name = "EncryptedCryptoIV", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<String> encryptedCryptoIV;
    @XmlElementRef(name = "EncryptedCryptoKey", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<String> encryptedCryptoKey;

    public JAXBElement<String> getEncryptedCryptoIV() {
        return encryptedCryptoIV;
    }

    public void setEncryptedCryptoIV(JAXBElement<String> value) {
        this.encryptedCryptoIV = value;
    }

    public JAXBElement<String> getEncryptedCryptoKey() {
        return encryptedCryptoKey;
    }

    public void setEncryptedCryptoKey(JAXBElement<String> value) {
        this.encryptedCryptoKey = value;
    }
}