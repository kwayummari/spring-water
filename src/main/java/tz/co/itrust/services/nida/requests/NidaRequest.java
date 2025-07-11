package tz.co.itrust.services.nida.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties
public class NidaRequest {
	private String nin;		
	private String rqCode;
	private String qnAnsw;	
}
