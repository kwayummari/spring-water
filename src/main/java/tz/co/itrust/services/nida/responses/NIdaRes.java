package tz.co.itrust.services.nida.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NIdaRes {
    private String nationality;
    private String phoneNumber;
    private String gender;    
    private String email;
    private String firstName;
    private String middleName;
    private String photo;
    private String lastName;
    private String nin;
    private String physicalAddress;
    private String dob;
    private String rqCode;
    private String preQnAnsw;
    private String qnEn;
    private String qnSw;
    private String country;
    private String region;
    private String placeOfBirth;
    private String birthDistrict;
    private String birthWard;
    private String residentDistrict;
    private String residentRegion;
    private String residentVillage;
    private String otherNames;
    private String residentPostCode;
    private String residentHouseNo;
    
	public NIdaRes(String nationality, String phoneNumber, String gender, String email, String firstName,
			String middleName, String photo, String lastName, String nin, String physicalAddress, String dob,
			String rqCode, String preQnAnsw, String qnEn, String qnSw, String region, String country) {
		super();
		this.nationality = nationality;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.email = email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.photo = photo;
		this.lastName = lastName;
		this.nin = nin;
		this.physicalAddress = physicalAddress;
		this.dob = dob;
		this.rqCode = rqCode;
		this.preQnAnsw = preQnAnsw;
		this.qnEn = qnEn;
		this.qnSw = qnSw;
		this.region = region;
		this.country = country;
	}    
}
