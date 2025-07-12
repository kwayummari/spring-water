package tz.co.itrust.services.nida.soap.datacontract;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestHeader", propOrder = {
    "clientNameOrIP",
    "id",
    "timeStamp",
    "userId"
})
public class RequestHeader {

    @XmlElementRef(name = "ClientNameOrIP", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientNameOrIP;
    @XmlElementRef(name = "Id", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<String> id;
    @XmlElementRef(name = "TimeStamp", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<String> timeStamp;
    @XmlElementRef(name = "UserId", namespace = "http://schemas.datacontract.org/2004/07/NID_API", type = JAXBElement.class, required = false)
    protected JAXBElement<String> userId;

    public JAXBElement<String> getClientNameOrIP() {
        return clientNameOrIP;
    }

    public void setClientNameOrIP(JAXBElement<String> value) {
        this.clientNameOrIP = value;
    }

    public JAXBElement<String> getId() {
        return id;
    }

    public void setId(JAXBElement<String> value) {
        this.id = value;
    }

    public JAXBElement<String> getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(JAXBElement<String> value) {
        this.timeStamp = value;
    }

    public JAXBElement<String> getUserId() {
        return userId;
    }

    public void setUserId(JAXBElement<String> value) {
        this.userId = value;
    }
}