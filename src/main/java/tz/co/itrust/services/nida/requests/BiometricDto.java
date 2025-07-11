package tz.co.itrust.services.nida.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties
public class BiometricDto {
    private String nin;
    private String fingerCode;
    private String fingerPrintImage;
}
