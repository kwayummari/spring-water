package tz.co.itrust.services.nida.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for NIDA verification results
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "NIDA verification response containing citizen information")
public class NidaVerificationResponseDto {

    @Schema(description = "National Identification Number")
    @JsonProperty("nin")
    private String nin;

    @Schema(description = "First name")
    @JsonProperty("firstName")
    private String firstName;

    @Schema(description = "Middle name")
    @JsonProperty("middleName")
    private String middleName;

    @Schema(description = "Surname/Last name")
    @JsonProperty("surname")
    private String surname;

    @Schema(description = "Other names")
    @JsonProperty("otherNames")
    private String otherNames;

    @Schema(description = "Gender")
    @JsonProperty("gender")
    private String gender;

    @Schema(description = "Email address")
    @JsonProperty("email")
    private String email;

    @Schema(description = "Date of birth")
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

    @Schema(description = "Place of birth")
    @JsonProperty("placeOfBirth")
    private String placeOfBirth;

    @Schema(description = "Nationality")
    @JsonProperty("nationality")
    private String nationality;

    @Schema(description = "Phone number")
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @Schema(description = "Marital status")
    @JsonProperty("maritalStatus")
    private String maritalStatus;

    @Schema(description = "Base64 encoded photo")
    @JsonProperty("photo")
    private String photo;

    @Schema(description = "Base64 encoded signature")
    @JsonProperty("signature")
    private String signature;

    // Address information
    @Schema(description = "Resident region")
    @JsonProperty("residentRegion")
    private String residentRegion;

    @Schema(description = "Resident district")
    @JsonProperty("residentDistrict")
    private String residentDistrict;

    @Schema(description = "Resident ward")
    @JsonProperty("residentWard")
    private String residentWard;

    @Schema(description = "Resident village")
    @JsonProperty("residentVillage")
    private String residentVillage;

    @Schema(description = "Resident house number")
    @JsonProperty("residentHouseNo")
    private String residentHouseNo;

    @Schema(description = "Resident postal address")
    @JsonProperty("residentPostalAddress")
    private String residentPostalAddress;

    @Schema(description = "Resident post code")
    @JsonProperty("residentPostCode")
    private String residentPostCode;

    // Birth place information
    @Schema(description = "Birth country")
    @JsonProperty("birthCountry")
    private String birthCountry;

    @Schema(description = "Birth region")
    @JsonProperty("birthRegion")
    private String birthRegion;

    @Schema(description = "Birth district")
    @JsonProperty("birthDistrict")
    private String birthDistrict;

    @Schema(description = "Birth ward")
    @JsonProperty("birthWard")
    private String birthWard;

    // Questionnaire information
    @Schema(description = "Question in English")
    @JsonProperty("questionEn")
    private String questionEn;

    @Schema(description = "Question in Swahili")
    @JsonProperty("questionSw")
    private String questionSw;

    @Schema(description = "Request/Question code")
    @JsonProperty("rqCode")
    private String rqCode;

    @Schema(description = "Previous answer code")
    @JsonProperty("previousAnswerCode")
    private String previousAnswerCode;

    @Schema(description = "Registered fingerprints")
    @JsonProperty("registeredFingerprints")
    private String registeredFingerprints;

    @Schema(description = "Verification status")
    @JsonProperty("verificationStatus")
    private VerificationStatus verificationStatus;

    @Schema(description = "Verification message")
    @JsonProperty("message")
    private String message;

    public enum VerificationStatus {
        SUCCESS,
        PENDING_QUESTION,
        FAILED,
        NIN_NOT_FOUND,
        INVALID_ANSWER
    }
}