package tz.co.itrust.services.nida.requests;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@XmlRootElement(name = "Payload")
public class NidaNINPayload {
	
	@XmlElement(name = "NIN")
	private String nin;
	
	@XmlElement(name = "RQCode")
	private String rqCode;
	
	@XmlElement(name = "QNANSW")
	private String qnAnsw;
	
	public void setNinValue(String nin) {
		this.nin = nin;
	}

	public String getNinValue() {
		return nin;
	}

	public NidaNINPayload(String nin) {
		this.nin = nin;
	}
	public NidaNINPayload(String nin, String rqCode, String qnAnsw) {
		this.nin = nin;
		this.rqCode = rqCode;
		this.qnAnsw = qnAnsw;
	}
}
