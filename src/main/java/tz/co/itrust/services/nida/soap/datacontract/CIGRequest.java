package tz.co.itrust.services.nida.soap.datacontract;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

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

    public JAXBElement<RequestBody> getBody() {
        return body;
    }

    public void setBody(JAXBElement<RequestBody> value) {
        this.body = value;
    }

    public JAXBElement<RequestHeader> getHeader() {
        return header;
    }

    public void setHeader(JAXBElement<RequestHeader> value) {
        this.header = value;
    }
}