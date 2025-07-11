
package tz.co.itrust.services.nida.tempuri;

import tz.co.itrust.services.nida.datacontract.schemas.CIGResponse;

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
 *         &lt;element name="TransactionVerificationResult" type="{http://schemas.datacontract.org/2004/07/NID_API}CIGResponse" minOccurs="0"/>
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
    "transactionVerificationResult"
})
@XmlRootElement(name = "TransactionVerificationResponse")
public class TransactionVerificationResponse {

    @XmlElementRef(name = "TransactionVerificationResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<CIGResponse> transactionVerificationResult;

    /**
     * Gets the value of the transactionVerificationResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CIGResponse }{@code >}
     *     
     */
    public JAXBElement<CIGResponse> getTransactionVerificationResult() {
        return transactionVerificationResult;
    }

    /**
     * Sets the value of the transactionVerificationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CIGResponse }{@code >}
     *     
     */
    public void setTransactionVerificationResult(JAXBElement<CIGResponse> value) {
        this.transactionVerificationResult = value;
    }

}
