package tz.co.itrust.services.nida.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity for storing NIDA verification results
 */
@Entity
@Table(name = "nida_verifications", indexes = {
    @Index(name = "idx_nin", columnList = "nin"),
    @Index(name = "idx_verification_date", columnList = "verificationDate"),
    @Index(name = "idx_status", columnList = "verificationStatus")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NidaVerificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nin", nullable = false, length = 20)
    private String nin;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "other_names")
    private String otherNames;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "nationality", length = 50)
    private String nationality;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "marital_status", length = 20)
    private String maritalStatus;

    @Lob
    @Column(name = "photo")
    private String photo;

    @Lob
    @Column(name = "signature")
    private String signature;

    // Address information
    @Column(name = "resident_region")
    private String residentRegion;

    @Column(name = "resident_district")
    private String residentDistrict;

    @Column(name = "resident_ward")
    private String residentWard;

    @Column(name = "resident_village")
    private String residentVillage;

    @Column(name = "resident_house_no")
    private String residentHouseNo;

    @Column(name = "resident_postal_address")
    private String residentPostalAddress;

    @Column(name = "resident_post_code", length = 10)
    private String residentPostCode;

    // Birth place information
    @Column(name = "birth_country")
    private String birthCountry;

    @Column(name = "birth_region")
    private String birthRegion;

    @Column(name = "birth_district")
    private String birthDistrict;

    @Column(name = "birth_ward")
    private String birthWard;

    // Verification metadata
    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status", nullable = false)
    private VerificationStatus verificationStatus;

    @Column(name = "verification_type", nullable = false)
    private String verificationType; // "QUESTIONNAIRE" or "BIOMETRIC"

    @Column(name = "rq_code")
    private String rqCode;

    @Column(name = "previous_answer_code")
    private String previousAnswerCode;

    @Column(name = "registered_fingerprints")
    private String registeredFingerprints;

    @Column(name = "verification_message")
    private String verificationMessage;

    @CreationTimestamp
    @Column(name = "verification_date", nullable = false)
    private LocalDateTime verificationDate;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "request_ip")
    private String requestIp;

    @Column(name = "user_agent")
    private String userAgent;

    public enum VerificationStatus {
        SUCCESS,
        PENDING_QUESTION,
        FAILED,
        NIN_NOT_FOUND,
        INVALID_ANSWER
    }
}