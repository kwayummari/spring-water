package tz.co.itrust.services.nida.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Request DTO for biometric verification
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Biometric verification request")
public class BiometricVerificationRequestDto {

    @Schema(description = "National Identification Number", 
            example = "19801225123456789012", 
            required = true)
    @NotBlank(message = "NIN is required")
    @Pattern(regexp = "^[0-9]{20}$", message = "NIN must be exactly 20 digits")
    @JsonProperty("nin")
    private String nin;

    @Schema(description = "Finger code identifier", 
            example = "01", 
            required = true)
    @NotBlank(message = "Finger code is required")
    @Pattern(regexp = "^[0-9]{2}$", message = "Finger code must be exactly 2 digits")
    @JsonProperty("fingerCode")
    private String fingerCode;

    @Schema(description = "Base64 encoded fingerprint image", 
            required = true)
    @NotBlank(message = "Fingerprint image is required")
    @JsonProperty("fingerprintImage")
    private String fingerprintImage;
}