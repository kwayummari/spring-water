package tz.co.itrust.services.nida.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.UUID;

@Entity
public class NidaResponseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    @Column(
            name = "id",
            nullable = false
    )
    @JdbcTypeCode(3000)
    private UUID id;
    public String nin;
    public String firstName;
    public String middleName;
    public String surName;
    public String otherNames;
    public String sex;
    public String email;
    public String dateOfBirth;
    public String placeOfBirth;
    public String residentRegion;
    public String residentDistrict;
    public String residentWard;
    public String residentVillage;
    public String residentHouseNo;
    public String residentPostalAddress;
    public String residentPostCode;
    public String birthCountry;
    public String birthRegion;
    public String birthDistrict;
    public String birthWard;
    public String nationality;
    public String phoneNumber;
    public String maritalStatus;
    public String photo;
    public String signaturePhoto;
    public String sw;
    public String en;
    public String rqCode;
    public String previousAnswecode;
    public String registeredFingerPrints;

    public UUID getId() {
        return this.id;
    }

    public String getNin() {
        return this.nin;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getSurName() {
        return this.surName;
    }

    public String getOtherNames() {
        return this.otherNames;
    }

    public String getSex() {
        return this.sex;
    }

    public String getEmail() {
        return this.email;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return this.placeOfBirth;
    }

    public String getResidentRegion() {
        return this.residentRegion;
    }

    public String getResidentDistrict() {
        return this.residentDistrict;
    }

    public String getResidentWard() {
        return this.residentWard;
    }

    public String getResidentVillage() {
        return this.residentVillage;
    }

    public String getResidentHouseNo() {
        return this.residentHouseNo;
    }

    public String getResidentPostalAddress() {
        return this.residentPostalAddress;
    }

    public String getResidentPostCode() {
        return this.residentPostCode;
    }

    public String getBirthCountry() {
        return this.birthCountry;
    }

    public String getBirthRegion() {
        return this.birthRegion;
    }

    public String getBirthDistrict() {
        return this.birthDistrict;
    }

    public String getBirthWard() {
        return this.birthWard;
    }

    public String getNationality() {
        return this.nationality;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getMaritalStatus() {
        return this.maritalStatus;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getSignaturePhoto() {
        return this.signaturePhoto;
    }

    public String getSw() {
        return this.sw;
    }

    public String getEn() {
        return this.en;
    }

    public String getRqCode() {
        return this.rqCode;
    }

    public String getPreviousAnswecode() {
        return this.previousAnswecode;
    }

    public String getRegisteredFingerPrints() {
        return this.registeredFingerPrints;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public void setNin(final String nin) {
        this.nin = nin;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public void setSurName(final String surName) {
        this.surName = surName;
    }

    public void setOtherNames(final String otherNames) {
        this.otherNames = otherNames;
    }

    public void setSex(final String sex) {
        this.sex = sex;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPlaceOfBirth(final String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void setResidentRegion(final String residentRegion) {
        this.residentRegion = residentRegion;
    }

    public void setResidentDistrict(final String residentDistrict) {
        this.residentDistrict = residentDistrict;
    }

    public void setResidentWard(final String residentWard) {
        this.residentWard = residentWard;
    }

    public void setResidentVillage(final String residentVillage) {
        this.residentVillage = residentVillage;
    }

    public void setResidentHouseNo(final String residentHouseNo) {
        this.residentHouseNo = residentHouseNo;
    }

    public void setResidentPostalAddress(final String residentPostalAddress) {
        this.residentPostalAddress = residentPostalAddress;
    }

    public void setResidentPostCode(final String residentPostCode) {
        this.residentPostCode = residentPostCode;
    }

    public void setBirthCountry(final String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public void setBirthRegion(final String birthRegion) {
        this.birthRegion = birthRegion;
    }

    public void setBirthDistrict(final String birthDistrict) {
        this.birthDistrict = birthDistrict;
    }

    public void setBirthWard(final String birthWard) {
        this.birthWard = birthWard;
    }

    public void setNationality(final String nationality) {
        this.nationality = nationality;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMaritalStatus(final String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setPhoto(final String photo) {
        this.photo = photo;
    }

    public void setSignaturePhoto(final String signaturePhoto) {
        this.signaturePhoto = signaturePhoto;
    }

    public void setSw(final String sw) {
        this.sw = sw;
    }

    public void setEn(final String en) {
        this.en = en;
    }

    public void setRqCode(final String rqCode) {
        this.rqCode = rqCode;
    }

    public void setPreviousAnswecode(final String previousAnswecode) {
        this.previousAnswecode = previousAnswecode;
    }

    public void setRegisteredFingerPrints(final String registeredFingerPrints) {
        this.registeredFingerPrints = registeredFingerPrints;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof NidaResponseEntity)) {
            return false;
        } else {
            NidaResponseEntity other = (NidaResponseEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label383: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label383;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label383;
                    }

                    return false;
                }

                Object this$nin = this.getNin();
                Object other$nin = other.getNin();
                if (this$nin == null) {
                    if (other$nin != null) {
                        return false;
                    }
                } else if (!this$nin.equals(other$nin)) {
                    return false;
                }

                Object this$firstName = this.getFirstName();
                Object other$firstName = other.getFirstName();
                if (this$firstName == null) {
                    if (other$firstName != null) {
                        return false;
                    }
                } else if (!this$firstName.equals(other$firstName)) {
                    return false;
                }

                label362: {
                    Object this$middleName = this.getMiddleName();
                    Object other$middleName = other.getMiddleName();
                    if (this$middleName == null) {
                        if (other$middleName == null) {
                            break label362;
                        }
                    } else if (this$middleName.equals(other$middleName)) {
                        break label362;
                    }

                    return false;
                }

                label355: {
                    Object this$surName = this.getSurName();
                    Object other$surName = other.getSurName();
                    if (this$surName == null) {
                        if (other$surName == null) {
                            break label355;
                        }
                    } else if (this$surName.equals(other$surName)) {
                        break label355;
                    }

                    return false;
                }

                Object this$otherNames = this.getOtherNames();
                Object other$otherNames = other.getOtherNames();
                if (this$otherNames == null) {
                    if (other$otherNames != null) {
                        return false;
                    }
                } else if (!this$otherNames.equals(other$otherNames)) {
                    return false;
                }

                Object this$sex = this.getSex();
                Object other$sex = other.getSex();
                if (this$sex == null) {
                    if (other$sex != null) {
                        return false;
                    }
                } else if (!this$sex.equals(other$sex)) {
                    return false;
                }

                label334: {
                    Object this$email = this.getEmail();
                    Object other$email = other.getEmail();
                    if (this$email == null) {
                        if (other$email == null) {
                            break label334;
                        }
                    } else if (this$email.equals(other$email)) {
                        break label334;
                    }

                    return false;
                }

                label327: {
                    Object this$dateOfBirth = this.getDateOfBirth();
                    Object other$dateOfBirth = other.getDateOfBirth();
                    if (this$dateOfBirth == null) {
                        if (other$dateOfBirth == null) {
                            break label327;
                        }
                    } else if (this$dateOfBirth.equals(other$dateOfBirth)) {
                        break label327;
                    }

                    return false;
                }

                Object this$placeOfBirth = this.getPlaceOfBirth();
                Object other$placeOfBirth = other.getPlaceOfBirth();
                if (this$placeOfBirth == null) {
                    if (other$placeOfBirth != null) {
                        return false;
                    }
                } else if (!this$placeOfBirth.equals(other$placeOfBirth)) {
                    return false;
                }

                label313: {
                    Object this$residentRegion = this.getResidentRegion();
                    Object other$residentRegion = other.getResidentRegion();
                    if (this$residentRegion == null) {
                        if (other$residentRegion == null) {
                            break label313;
                        }
                    } else if (this$residentRegion.equals(other$residentRegion)) {
                        break label313;
                    }

                    return false;
                }

                Object this$residentDistrict = this.getResidentDistrict();
                Object other$residentDistrict = other.getResidentDistrict();
                if (this$residentDistrict == null) {
                    if (other$residentDistrict != null) {
                        return false;
                    }
                } else if (!this$residentDistrict.equals(other$residentDistrict)) {
                    return false;
                }

                label299: {
                    Object this$residentWard = this.getResidentWard();
                    Object other$residentWard = other.getResidentWard();
                    if (this$residentWard == null) {
                        if (other$residentWard == null) {
                            break label299;
                        }
                    } else if (this$residentWard.equals(other$residentWard)) {
                        break label299;
                    }

                    return false;
                }

                Object this$residentVillage = this.getResidentVillage();
                Object other$residentVillage = other.getResidentVillage();
                if (this$residentVillage == null) {
                    if (other$residentVillage != null) {
                        return false;
                    }
                } else if (!this$residentVillage.equals(other$residentVillage)) {
                    return false;
                }

                Object this$residentHouseNo = this.getResidentHouseNo();
                Object other$residentHouseNo = other.getResidentHouseNo();
                if (this$residentHouseNo == null) {
                    if (other$residentHouseNo != null) {
                        return false;
                    }
                } else if (!this$residentHouseNo.equals(other$residentHouseNo)) {
                    return false;
                }

                label278: {
                    Object this$residentPostalAddress = this.getResidentPostalAddress();
                    Object other$residentPostalAddress = other.getResidentPostalAddress();
                    if (this$residentPostalAddress == null) {
                        if (other$residentPostalAddress == null) {
                            break label278;
                        }
                    } else if (this$residentPostalAddress.equals(other$residentPostalAddress)) {
                        break label278;
                    }

                    return false;
                }

                label271: {
                    Object this$residentPostCode = this.getResidentPostCode();
                    Object other$residentPostCode = other.getResidentPostCode();
                    if (this$residentPostCode == null) {
                        if (other$residentPostCode == null) {
                            break label271;
                        }
                    } else if (this$residentPostCode.equals(other$residentPostCode)) {
                        break label271;
                    }

                    return false;
                }

                Object this$birthCountry = this.getBirthCountry();
                Object other$birthCountry = other.getBirthCountry();
                if (this$birthCountry == null) {
                    if (other$birthCountry != null) {
                        return false;
                    }
                } else if (!this$birthCountry.equals(other$birthCountry)) {
                    return false;
                }

                Object this$birthRegion = this.getBirthRegion();
                Object other$birthRegion = other.getBirthRegion();
                if (this$birthRegion == null) {
                    if (other$birthRegion != null) {
                        return false;
                    }
                } else if (!this$birthRegion.equals(other$birthRegion)) {
                    return false;
                }

                label250: {
                    Object this$birthDistrict = this.getBirthDistrict();
                    Object other$birthDistrict = other.getBirthDistrict();
                    if (this$birthDistrict == null) {
                        if (other$birthDistrict == null) {
                            break label250;
                        }
                    } else if (this$birthDistrict.equals(other$birthDistrict)) {
                        break label250;
                    }

                    return false;
                }

                label243: {
                    Object this$birthWard = this.getBirthWard();
                    Object other$birthWard = other.getBirthWard();
                    if (this$birthWard == null) {
                        if (other$birthWard == null) {
                            break label243;
                        }
                    } else if (this$birthWard.equals(other$birthWard)) {
                        break label243;
                    }

                    return false;
                }

                Object this$nationality = this.getNationality();
                Object other$nationality = other.getNationality();
                if (this$nationality == null) {
                    if (other$nationality != null) {
                        return false;
                    }
                } else if (!this$nationality.equals(other$nationality)) {
                    return false;
                }

                Object this$phoneNumber = this.getPhoneNumber();
                Object other$phoneNumber = other.getPhoneNumber();
                if (this$phoneNumber == null) {
                    if (other$phoneNumber != null) {
                        return false;
                    }
                } else if (!this$phoneNumber.equals(other$phoneNumber)) {
                    return false;
                }

                label222: {
                    Object this$maritalStatus = this.getMaritalStatus();
                    Object other$maritalStatus = other.getMaritalStatus();
                    if (this$maritalStatus == null) {
                        if (other$maritalStatus == null) {
                            break label222;
                        }
                    } else if (this$maritalStatus.equals(other$maritalStatus)) {
                        break label222;
                    }

                    return false;
                }

                label215: {
                    Object this$photo = this.getPhoto();
                    Object other$photo = other.getPhoto();
                    if (this$photo == null) {
                        if (other$photo == null) {
                            break label215;
                        }
                    } else if (this$photo.equals(other$photo)) {
                        break label215;
                    }

                    return false;
                }

                Object this$signaturePhoto = this.getSignaturePhoto();
                Object other$signaturePhoto = other.getSignaturePhoto();
                if (this$signaturePhoto == null) {
                    if (other$signaturePhoto != null) {
                        return false;
                    }
                } else if (!this$signaturePhoto.equals(other$signaturePhoto)) {
                    return false;
                }

                label201: {
                    Object this$sw = this.getSw();
                    Object other$sw = other.getSw();
                    if (this$sw == null) {
                        if (other$sw == null) {
                            break label201;
                        }
                    } else if (this$sw.equals(other$sw)) {
                        break label201;
                    }

                    return false;
                }

                Object this$en = this.getEn();
                Object other$en = other.getEn();
                if (this$en == null) {
                    if (other$en != null) {
                        return false;
                    }
                } else if (!this$en.equals(other$en)) {
                    return false;
                }

                label187: {
                    Object this$rqCode = this.getRqCode();
                    Object other$rqCode = other.getRqCode();
                    if (this$rqCode == null) {
                        if (other$rqCode == null) {
                            break label187;
                        }
                    } else if (this$rqCode.equals(other$rqCode)) {
                        break label187;
                    }

                    return false;
                }

                Object this$previousAnswecode = this.getPreviousAnswecode();
                Object other$previousAnswecode = other.getPreviousAnswecode();
                if (this$previousAnswecode == null) {
                    if (other$previousAnswecode != null) {
                        return false;
                    }
                } else if (!this$previousAnswecode.equals(other$previousAnswecode)) {
                    return false;
                }

                Object this$registeredFingerPrints = this.getRegisteredFingerPrints();
                Object other$registeredFingerPrints = other.getRegisteredFingerPrints();
                if (this$registeredFingerPrints == null) {
                    if (other$registeredFingerPrints != null) {
                        return false;
                    }
                } else if (!this$registeredFingerPrints.equals(other$registeredFingerPrints)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof NidaResponseEntity;
    }

    public int hashCode() {
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $nin = this.getNin();
        result = result * 59 + ($nin == null ? 43 : $nin.hashCode());
        Object $firstName = this.getFirstName();
        result = result * 59 + ($firstName == null ? 43 : $firstName.hashCode());
        Object $middleName = this.getMiddleName();
        result = result * 59 + ($middleName == null ? 43 : $middleName.hashCode());
        Object $surName = this.getSurName();
        result = result * 59 + ($surName == null ? 43 : $surName.hashCode());
        Object $otherNames = this.getOtherNames();
        result = result * 59 + ($otherNames == null ? 43 : $otherNames.hashCode());
        Object $sex = this.getSex();
        result = result * 59 + ($sex == null ? 43 : $sex.hashCode());
        Object $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        Object $dateOfBirth = this.getDateOfBirth();
        result = result * 59 + ($dateOfBirth == null ? 43 : $dateOfBirth.hashCode());
        Object $placeOfBirth = this.getPlaceOfBirth();
        result = result * 59 + ($placeOfBirth == null ? 43 : $placeOfBirth.hashCode());
        Object $residentRegion = this.getResidentRegion();
        result = result * 59 + ($residentRegion == null ? 43 : $residentRegion.hashCode());
        Object $residentDistrict = this.getResidentDistrict();
        result = result * 59 + ($residentDistrict == null ? 43 : $residentDistrict.hashCode());
        Object $residentWard = this.getResidentWard();
        result = result * 59 + ($residentWard == null ? 43 : $residentWard.hashCode());
        Object $residentVillage = this.getResidentVillage();
        result = result * 59 + ($residentVillage == null ? 43 : $residentVillage.hashCode());
        Object $residentHouseNo = this.getResidentHouseNo();
        result = result * 59 + ($residentHouseNo == null ? 43 : $residentHouseNo.hashCode());
        Object $residentPostalAddress = this.getResidentPostalAddress();
        result = result * 59 + ($residentPostalAddress == null ? 43 : $residentPostalAddress.hashCode());
        Object $residentPostCode = this.getResidentPostCode();
        result = result * 59 + ($residentPostCode == null ? 43 : $residentPostCode.hashCode());
        Object $birthCountry = this.getBirthCountry();
        result = result * 59 + ($birthCountry == null ? 43 : $birthCountry.hashCode());
        Object $birthRegion = this.getBirthRegion();
        result = result * 59 + ($birthRegion == null ? 43 : $birthRegion.hashCode());
        Object $birthDistrict = this.getBirthDistrict();
        result = result * 59 + ($birthDistrict == null ? 43 : $birthDistrict.hashCode());
        Object $birthWard = this.getBirthWard();
        result = result * 59 + ($birthWard == null ? 43 : $birthWard.hashCode());
        Object $nationality = this.getNationality();
        result = result * 59 + ($nationality == null ? 43 : $nationality.hashCode());
        Object $phoneNumber = this.getPhoneNumber();
        result = result * 59 + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
        Object $maritalStatus = this.getMaritalStatus();
        result = result * 59 + ($maritalStatus == null ? 43 : $maritalStatus.hashCode());
        Object $photo = this.getPhoto();
        result = result * 59 + ($photo == null ? 43 : $photo.hashCode());
        Object $signaturePhoto = this.getSignaturePhoto();
        result = result * 59 + ($signaturePhoto == null ? 43 : $signaturePhoto.hashCode());
        Object $sw = this.getSw();
        result = result * 59 + ($sw == null ? 43 : $sw.hashCode());
        Object $en = this.getEn();
        result = result * 59 + ($en == null ? 43 : $en.hashCode());
        Object $rqCode = this.getRqCode();
        result = result * 59 + ($rqCode == null ? 43 : $rqCode.hashCode());
        Object $previousAnswecode = this.getPreviousAnswecode();
        result = result * 59 + ($previousAnswecode == null ? 43 : $previousAnswecode.hashCode());
        Object $registeredFingerPrints = this.getRegisteredFingerPrints();
        result = result * 59 + ($registeredFingerPrints == null ? 43 : $registeredFingerPrints.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = String.valueOf(this.getId());
        return "NidaResponseEntity(id=" + var10000 + ", nin=" + this.getNin() + ", firstName=" + this.getFirstName() + ", middleName=" + this.getMiddleName() + ", surName=" + this.getSurName() + ", otherNames=" + this.getOtherNames() + ", sex=" + this.getSex() + ", email=" + this.getEmail() + ", dateOfBirth=" + this.getDateOfBirth() + ", placeOfBirth=" + this.getPlaceOfBirth() + ", residentRegion=" + this.getResidentRegion() + ", residentDistrict=" + this.getResidentDistrict() + ", residentWard=" + this.getResidentWard() + ", residentVillage=" + this.getResidentVillage() + ", residentHouseNo=" + this.getResidentHouseNo() + ", residentPostalAddress=" + this.getResidentPostalAddress() + ", residentPostCode=" + this.getResidentPostCode() + ", birthCountry=" + this.getBirthCountry() + ", birthRegion=" + this.getBirthRegion() + ", birthDistrict=" + this.getBirthDistrict() + ", birthWard=" + this.getBirthWard() + ", nationality=" + this.getNationality() + ", phoneNumber=" + this.getPhoneNumber() + ", maritalStatus=" + this.getMaritalStatus() + ", photo=" + this.getPhoto() + ", signaturePhoto=" + this.getSignaturePhoto() + ", sw=" + this.getSw() + ", en=" + this.getEn() + ", rqCode=" + this.getRqCode() + ", previousAnswecode=" + this.getPreviousAnswecode() + ", registeredFingerPrints=" + this.getRegisteredFingerPrints() + ")";
    }
}
