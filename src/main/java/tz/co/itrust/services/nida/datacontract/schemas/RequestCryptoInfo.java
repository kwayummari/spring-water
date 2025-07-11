
package tz.co.itrust.services.nida.datacontract.schemas;


import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for RequestCryptoInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestCryptoInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EncryptedCryptoIV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EncryptedCryptoKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
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

    /**
     * Gets the value of the encryptedCryptoIV property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEncryptedCryptoIV() {
        return encryptedCryptoIV;
    }

    /**
     * Sets the value of the encryptedCryptoIV property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEncryptedCryptoIV(JAXBElement<String> value) {
        this.encryptedCryptoIV = value;
    }

    /**
     * Gets the value of the encryptedCryptoKey property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEncryptedCryptoKey() {
        return encryptedCryptoKey;
    }

    /**
     * Sets the value of the encryptedCryptoKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEncryptedCryptoKey(JAXBElement<String> value) {
        this.encryptedCryptoKey = value;
    }

}
