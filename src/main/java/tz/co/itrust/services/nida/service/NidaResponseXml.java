package tz.co.itrust.services.nida.service;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * XML mapping class for NIDA response
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "NidaResponse")
public class NidaResponseXml {
    
    @XmlElement(name = "NIN")
    private String nin;
    
    @XmlElement(name = "FIRSTNAME")
    private String firstName;
    
    @XmlElement(name = "MIDDLENAME")
    private String middleName;
    
    @XmlElement(name = "SURNAME")
    private String surname;
    
    @XmlElement(name = "OTHERNAMES")
    private String otherNames;
    
    @XmlElement(name = "SEX")
    private String gender;
    
    @XmlElement(name = "EMAIL")
    private String email;
    
    @XmlElement(name = "DATEOFBIRTH")
    private String dateOfBirth;
    
    @XmlElement(name = "PLACEOFBIRTH")
    private String placeOfBirth;
    
    @XmlElement(name = "NATIONALITY")
    private String nationality;
    
    @XmlElement(name = "PHONENUMBER")
    private String phoneNumber;
    
    @XmlElement(name = "MARITALSTATUS")
    private String maritalStatus;
    
    @XmlElement(name = "PHOTO")
    private String photo;
    
    @XmlElement(name = "SIGNATURE")
    private String signature;
    
    // Address information
    @XmlElement(name = "RESIDENTREGION")
    private String residentRegion;
    
    @XmlElement(name = "RESIDENTDISTRICT")
    private String residentDistrict;
    
    @XmlElement(name = "RESIDENTWARD")
    private String residentWard;
    
    @XmlElement(name = "RESIDENTVILLAGE")
    private String residentVillage;
    
    @XmlElement(name = "RESIDENTHOUSENO")
    private String residentHouseNo;
    
    @XmlElement(name = "RESIDENTPOSTALADDRESS")
    private String residentPostalAddress;
    
    @XmlElement(name = "RESIDENTPOSTCODE")
    private String residentPostCode;
    
    // Birth place information
    @XmlElement(name = "BIRTHCOUNTRY")
    private String birthCountry;
    
    @XmlElement(name = "BIRTHREGION")
    private String birthRegion;
    
    @XmlElement(name = "BIRTHDISTRICT")
    private String birthDistrict;
    
    @XmlElement(name = "BIRTHWARD")
    private String birthWard;
    
    // Questionnaire information
    @XmlElement(name = "EN")
    private String questionEn;
    
    @XmlElement(name = "SW")
    private String questionSw;
    
    @XmlElement(name = "RQCode")
    private String rqCode;
    
    @XmlElement(name = "PREV_ANSW_CODE")
    private String previousAnswerCode;
    
    @XmlElement(name = "Fingerprints")
    private String registeredFingerprints;

    // Explicit getter methods (in case Lombok doesn't work in your IDE)
    public String getNin() {
        return nin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getPhoto() {
        return photo;
    }

    public String getSignature() {
        return signature;
    }

    public String getResidentRegion() {
        return residentRegion;
    }

    public String getResidentDistrict() {
        return residentDistrict;
    }

    public String getResidentWard() {
        return residentWard;
    }

    public String getResidentVillage() {
        return residentVillage;
    }

    public String getResidentHouseNo() {
        return residentHouseNo;
    }

    public String getResidentPostalAddress() {
        return residentPostalAddress;
    }

    public String getResidentPostCode() {
        return residentPostCode;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public String getBirthRegion() {
        return birthRegion;
    }

    public String getBirthDistrict() {
        return birthDistrict;
    }

    public String getBirthWard() {
        return birthWard;
    }

    public String getQuestionEn() {
        return questionEn;
    }

    public String getQuestionSw() {
        return questionSw;
    }

    public String getRqCode() {
        return rqCode;
    }

    public String getPreviousAnswerCode() {
        return previousAnswerCode;
    }

    public String getRegisteredFingerprints() {
        return registeredFingerprints;
    }

    // Explicit setter methods (in case Lombok doesn't work)
    public void setNin(String nin) {
        this.nin = nin;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setResidentRegion(String residentRegion) {
        this.residentRegion = residentRegion;
    }

    public void setResidentDistrict(String residentDistrict) {
        this.residentDistrict = residentDistrict;
    }

    public void setResidentWard(String residentWard) {
        this.residentWard = residentWard;
    }

    public void setResidentVillage(String residentVillage) {
        this.residentVillage = residentVillage;
    }

    public void setResidentHouseNo(String residentHouseNo) {
        this.residentHouseNo = residentHouseNo;
    }

    public void setResidentPostalAddress(String residentPostalAddress) {
        this.residentPostalAddress = residentPostalAddress;
    }

    public void setResidentPostCode(String residentPostCode) {
        this.residentPostCode = residentPostCode;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public void setBirthRegion(String birthRegion) {
        this.birthRegion = birthRegion;
    }

    public void setBirthDistrict(String birthDistrict) {
        this.birthDistrict = birthDistrict;
    }

    public void setBirthWard(String birthWard) {
        this.birthWard = birthWard;
    }

    public void setQuestionEn(String questionEn) {
        this.questionEn = questionEn;
    }

    public void setQuestionSw(String questionSw) {
        this.questionSw = questionSw;
    }

    public void setRqCode(String rqCode) {
        this.rqCode = rqCode;
    }

    public void setPreviousAnswerCode(String previousAnswerCode) {
        this.previousAnswerCode = previousAnswerCode;
    }

    public void setRegisteredFingerprints(String registeredFingerprints) {
        this.registeredFingerprints = registeredFingerprints;
    }
}