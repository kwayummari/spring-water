package tz.co.itrust.services.nida.soap.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * SOAP payload for NIDA NIN verification
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "Payload")
public class NidaNINPayload {
    
    @XmlElement(name = "NIN")
    private String nin;
    
    @XmlElement(name = "RQCode")
    private String rqCode;
    
    @XmlElement(name = "QNANSW")
    private String qnAnswer;
    
    public NidaNINPayload(String nin) {
        this.nin = nin;
    }
    
    public NidaNINPayload(String nin, String rqCode, String qnAnswer) {
        this.nin = nin;
        this.rqCode = rqCode;
        this.qnAnswer = qnAnswer;
    }
}