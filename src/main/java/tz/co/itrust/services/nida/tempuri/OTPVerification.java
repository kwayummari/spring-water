
package tz.co.itrust.services.nida.tempuri;

import tz.co.itrust.services.nida.datacontract.schemas.CIGRequest;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;




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
 *         &lt;element name="iRequest" type="{http://schemas.datacontract.org/2004/07/NID_API}CIGRequest" minOccurs="0"/>
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
    "iRequest"
})
@XmlRootElement(name = "OTPVerification")
public class OTPVerification {

    @XmlElementRef(name = "iRequest", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<CIGRequest> iRequest;

    /**
     * Gets the value of the iRequest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CIGRequest }{@code >}
     *     
     */
    public JAXBElement<CIGRequest> getIRequest() {
        return iRequest;
    }

    /**
     * Sets the value of the iRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CIGRequest }{@code >}
     *     
     */
    public void setIRequest(JAXBElement<CIGRequest> value) {
        this.iRequest = value;
    }

}
