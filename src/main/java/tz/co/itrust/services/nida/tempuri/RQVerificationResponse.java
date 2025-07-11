
package tz.co.itrust.services.nida.tempuri;

import jakarta.xml.bind.annotation.*;
import tz.co.itrust.services.nida.datacontract.schemas.CIGResponse;

import jakarta.xml.bind.JAXBElement;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RQVerificationResult" type="{http://schemas.datacontract.org/2004/07/NID_API}CIGResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rqVerificationResult"
})
@XmlRootElement(name = "RQVerificationResponse", namespace = "http://tempuri.org/")
public class RQVerificationResponse {

    @XmlElementRef(name = "RQVerificationResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<CIGResponse> rqVerificationResult;

    /**
     * Gets the value of the rqVerificationResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CIGResponse }{@code >}
     *     
     */
    public JAXBElement<CIGResponse> getRQVerificationResult() {
        return rqVerificationResult;
    }

    /**
     * Sets the value of the rqVerificationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CIGResponse }{@code >}
     *     
     */
    public void setRQVerificationResult(JAXBElement<CIGResponse> value) {
        this.rqVerificationResult = value;
    }

}
