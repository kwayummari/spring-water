package tz.co.itrust.services.nida.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Request DTO for NIDA verification with questionnaire
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "NIDA verification request with optional questionnaire")
public class NidaVerificationRequestDto {

    @Schema(description = "National Identification Number", 
            example = "19801225123456789012", 
            required = true)
    @NotBlank(message = "NIN is required")
    @Pattern(regexp = "^[0-9]{20}$", message = "NIN must be exactly 20 digits")
    @JsonProperty("nin")
    private String nin;

    @Schema(description = "Request/Question code from previous response", 
            example = "RQ001")
    @Size(max = 50, message = "RQ code must not exceed 50 characters")
    @JsonProperty("rqCode")
    private String rqCode;

    @Schema(description = "Answer to the previous question", 
            example = "DODOMA")
    @Size(max = 200, message = "Answer must not exceed 200 characters")
    @JsonProperty("qnAnswer")
    private String qnAnswer;
}