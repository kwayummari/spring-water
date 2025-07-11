package tz.co.itrust.services.nida.responses;

import jakarta.xml.bind.annotation.*;
import lombok.Setter;


@Setter
@XmlRootElement(name="NidaResponse")
public class NidaResponse {
	
	@XmlElement(name="NIN")
	public String nin;
	
	@XmlElement(name="FIRSTNAME")
	public String firstName;
	
	
	@XmlElement(name="MIDDLENAME")
	public String middleName;
	
	
	@XmlElement(name="SURNAME")
	public String surName;
	
	
	@XmlElement(name="OTHERNAMES")
	public String otherNames;
	
	
	@XmlElement(name="SEX")
	public String sex;
	
	@XmlElement(name="EMAIL")
	public String email;
	
	@XmlElement(name="DATEOFBIRTH")
	public String dateOfBirth;
	
	@XmlElement(name="PLACEOFBIRTH")
	public String placeOfBirth;
	
	
	@XmlElement(name="RESIDENTREGION")
	public String residentRegion;
	
	@XmlElement(name="RESIDENTDISTRICT")
	public String residentDistrict;
	
	@XmlElement(name="RESIDENTWARD")
	public String residentWard;
	
	@XmlElement(name="RESIDENTVILLAGE")
	public String residentVillage;
	
	@XmlElement(name="RESIDENTHOUSENO")
	public String residentHouseNo;
	
	@XmlElement(name="RESIDENTPOSTALADDRESS")
	public String residentPostalAddress;
	
	@XmlElement(name="RESIDENTPOSTCODE")
	public String residentPostCode;
	
	@XmlElement(name="BIRTHCOUNTRY")
	public String birthCountry;
	
	@XmlElement(name="BIRTHREGION")
	public String birthRegion;
	
	@XmlElement(name="BIRTHDISTRICT")
	public String birthDistrict;
	
	@XmlElement(name="BIRTHWARD")
	public String birthWard;
	
	@XmlElement(name="NATIONALITY")
	public String nationality;
	
	@XmlElement(name="PHONENUMBER")
	public String phoneNumber;
	
	@XmlElement(name="MARITALSTATUS")
	public String maritalStatus;
	
	@XmlElement(name="PHOTO")
	public String photo;
	
	@XmlElement(name="SIGNATURE")
	public String signaturePhoto;
	
	@XmlElement(name="SW")
	public String sw;
	
	@XmlElement(name="EN")
	public String en;
	
	@XmlElement(name="RQCode")
	public String rqCode;
	
	@XmlElement(name="PREV_ANSW_CODE")
	public String previousAnswecode;

	@XmlElement(name="Fingerprints")
	public String registeredFingerPrints;

	public String getPreviousAnswecodeValue() {
		return previousAnswecode;
	}

	public String getNinValue() {
		return nin;
	}

	public String getSwValue() {
		return sw;
	}

	public String getEnValue() {
		return en;
	}

	public String getRqCodeValue() {
		return rqCode;
	}

	public String getFirstNameValue() {
		return firstName;
	}

	public String getMiddleNameValue() {
		return middleName;
	}

	public String getSurNameValue() {
		return surName;
	}

	public String getSexValue() {
		return sex;
	}

	public String getDateOfBirthValue() {
		return dateOfBirth;
	}

	public String getNationalityValue() {
		return nationality;
	}

	public String getPhoneNumberValue() {
		return phoneNumber;
	}

	public String getMaritalStatusValue() {
		return maritalStatus;
	}

	public String getPhotoValue() {
		return photo;
	}

	public String getEmailValue() {
		return email;
	}	
	
	public String getResidentPostalAddressValue() {
		return residentPostalAddress;
	}
	
	public String getBirthRegionValue() {
		return this.birthRegion;
	}
	
	public String getCountryValue() {
		return this.birthCountry;
	}
	public String getPlaceofBirthValue() {
		return this.placeOfBirth;
	}
	
	public String getBirthDistrictValue() {
		return this.birthDistrict;
	}
	public String getBirthWardValue() {
		return this.birthWard;
	}
	public String getResidentDistrictValue() {
		return this.residentDistrict;
	}
	public String getresidentRegionValue() {
		return this.residentRegion;
	}
	public String getResidentVillageValue() {
		return this.residentVillage;
	}
	public String getOtherNamesValue() {
		return this.otherNames;
	}
	public String getResidentHouseNoValue() {
		return this.residentHouseNo;
	}
	
	public String getResidentPostCodeValue() {
		return this.residentPostCode;
	}
	public String getRegisteredFingerPrintsValue(){return this.registeredFingerPrints;}
	
	
	
	
	
}
