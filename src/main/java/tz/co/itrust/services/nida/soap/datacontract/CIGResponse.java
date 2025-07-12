package tz.co.itrust.services.nida.soap.datacontract;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIGResponse", propOrder = {
    "body",
    "code",
    "header"
})
public class CIGResponse {

    @XmlElementRef(name = "Body", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<ResponseBody> body;
    @XmlElementRef(name = "Code", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<String> code;
    @XmlElementRef(name = "Header", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<ResponseHeader> header;

    public JAXBElement<ResponseBody> getBody() {
        return body;
    }

    public void setBody(JAXBElement<ResponseBody> value) {
        this.body = value;
    }

    public JAXBElement<String> getCode() {
        return code;
    }

    public void setCode(JAXBElement<String> value) {
        this.code = value;
    }

    public JAXBElement<ResponseHeader> getHeader() {
        return header;
    }

    public void setHeader(JAXBElement<ResponseHeader> value) {
        this.header = value;
    }
}
