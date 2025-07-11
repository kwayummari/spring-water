
package tz.co.itrust.services.nida.datacontract.schemas;


import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for ResponseBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CryptoInfo" type="{http://schemas.datacontract.org/2004/07/NID_API}ResponseCryptoInfo" minOccurs="0"/>
 *         &lt;element name="Payload" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Signature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
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

    /**
     * Gets the value of the cryptoInfo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ResponseCryptoInfo }{@code >}
     *     
     */
    public JAXBElement<ResponseCryptoInfo> getCryptoInfo() {
        return cryptoInfo;
    }

    /**
     * Sets the value of the cryptoInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ResponseCryptoInfo }{@code >}
     *     
     */
    public void setCryptoInfo(JAXBElement<ResponseCryptoInfo> value) {
        this.cryptoInfo = value;
    }

    /**
     * Gets the value of the payload property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPayload(JAXBElement<String> value) {
        this.payload = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSignature(JAXBElement<String> value) {
        this.signature = value;
    }

}
