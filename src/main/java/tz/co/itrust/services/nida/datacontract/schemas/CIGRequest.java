
package tz.co.itrust.services.nida.datacontract.schemas;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CIGRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CIGRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Body" type="{http://schemas.datacontract.org/2004/07/NID_API}RequestBody" minOccurs="0"/>
 *         &lt;element name="Header" type="{http://schemas.datacontract.org/2004/07/NID_API}RequestHeader" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIGRequest", propOrder = {
    "body",
    "header"
})
public class CIGRequest {

    @XmlElementRef(name = "Body", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<RequestBody> body;
    @XmlElementRef(name = "Header", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<RequestHeader> header;

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RequestBody }{@code >}
     *     
     */
    public JAXBElement<RequestBody> getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RequestBody }{@code >}
     *     
     */
    public void setBody(JAXBElement<RequestBody> value) {
        this.body = value;
    }

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RequestHeader }{@code >}
     *     
     */
    public JAXBElement<RequestHeader> getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RequestHeader }{@code >}
     *     
     */
    public void setHeader(JAXBElement<RequestHeader> value) {
        this.header = value;
    }

}
