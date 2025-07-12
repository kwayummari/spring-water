package tz.co.itrust.services.nida.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Service;
import tz.co.itrust.services.nida.dto.response.NidaVerificationResponseDto;
import tz.co.itrust.services.nida.exceptions.NidaServiceException;
import tz.co.itrust.services.nida.security.CryptoService;
import tz.co.itrust.services.nida.soap.datacontract.CIGResponse;
import tz.co.itrust.services.nida.soap.datacontract.ResponseBody;
import tz.co.itrust.services.nida.soap.datacontract.ResponseCryptoInfo;
import tz.co.itrust.services.nida.soap.tempuri.RQVerificationResponse;
import tz.co.itrust.services.nida.soap.tempuri.VerificationResponse;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Service for processing and decrypting NIDA responses
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseProcessingService {

    private final CryptoService cryptoService;

    /**
     * Process standard NIDA verification response
     */
    public NidaVerificationResponseDto processVerificationResponse(RQVerificationResponse soapResponse) {
        log.debug("Processing NIDA verification response");
        
        try {
            // Extract response body
            CIGResponse cigResponse = soapResponse.getRQVerificationResult().getValue();
            ResponseBody responseBody = cigResponse.getBody().getValue();
            
            // Verify signature and decrypt payload
            String decryptedXml = decryptAndVerifyPayload(responseBody);
            
            // Parse decrypted XML to response DTO
            NidaResponseXml nidaXml = parseNidaResponseXml(decryptedXml);
            
            // Convert to DTO
            return mapToResponseDto(nidaXml);
            
        } catch (Exception e) {
            log.error("Failed to process NIDA verification response", e);
            throw new NidaServiceException("Failed to process verification response", e);
        }
    }

    /**
     * Process biometric verification response
     */
    public NidaVerificationResponseDto processBiometricResponse(VerificationResponse soapResponse) {
        log.debug("Processing biometric verification response");
        
        try {
            // Extract response body
            CIGResponse cigResponse = soapResponse.getBiometricVerificationResult().getValue();
            ResponseBody responseBody = cigResponse.getBody().getValue();
            
            // Verify signature and decrypt payload
            String decryptedXml = decryptAndVerifyPayload(responseBody);
            
            // Parse decrypted XML to response DTO
            NidaResponseXml nidaXml = parseNidaResponseXml(decryptedXml);
            
            // Convert to DTO
            return mapToResponseDto(nidaXml);
            
        } catch (Exception e) {
            log.error("Failed to process biometric verification response", e);
            throw new NidaServiceException("Failed to process biometric response", e);
        }
    }

    private String decryptAndVerifyPayload(ResponseBody responseBody) {
        try {
            // Extract encrypted data
            String base64Payload = responseBody.getPayload().getValue();
            String base64Signature = responseBody.getSignature().getValue();
            
            byte[] encryptedPayload = Base64.decode(base64Payload);
            byte[] signature = Base64.decode(base64Signature);
            
            // Verify signature
            boolean signatureValid = cryptoService.verifySignature(encryptedPayload, signature);
            if (!signatureValid) {
                throw new NidaServiceException("Invalid response signature from NIDA");
            }
            
            log.debug("Response signature verified successfully");
            
            // Extract and decrypt keys
            ResponseCryptoInfo cryptoInfo = responseBody.getCryptoInfo().getValue();
            String base64Key = cryptoInfo.getEncryptedCryptoKey().getValue();
            String base64IV = cryptoInfo.getEncryptedCryptoIV().getValue();
            
            byte[] decryptedKey = cryptoService.decryptRSA(Base64.decode(base64Key));
            byte[] decryptedIV = cryptoService.decryptRSA(Base64.decode(base64IV));
            
            // Decrypt payload
            byte[] decryptedPayload = cryptoService.decryptRijndael256(encryptedPayload, decryptedKey, decryptedIV);
            
            String decryptedXml = new String(decryptedPayload).trim();
            log.debug("Response payload decrypted successfully");
            
            return decryptedXml;
            
        } catch (Exception e) {
            log.error("Failed to decrypt response payload", e);
            throw new NidaServiceException("Failed to decrypt response", e);
        }
    }

    private NidaResponseXml parseNidaResponseXml(String xmlString) {
        try {
            // Clean up XML string
            int lastBracketIndex = xmlString.lastIndexOf('>');
            if (lastBracketIndex > 0) {
                xmlString = xmlString.substring(0, lastBracketIndex + 1);
            }
            
            // Parse XML
            JAXBContext context = JAXBContext.newInstance(NidaResponseXml.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            
            StringReader reader = new StringReader(xmlString);
            NidaResponseXml response = (NidaResponseXml) unmarshaller.unmarshal(reader);
            reader.close();
            
            log.debug("NIDA response XML parsed successfully");
            return response;
            
        } catch (Exception e) {
            log.error("Failed to parse NIDA response XML: {}", xmlString, e);
            throw new NidaServiceException("Failed to parse response XML", e);
        }
    }

    private NidaVerificationResponseDto mapToResponseDto(NidaResponseXml xmlResponse) {
        return NidaVerificationResponseDto.builder()
            .nin(xmlResponse.getNin())
            .firstName(xmlResponse.getFirstName())
            .middleName(xmlResponse.getMiddleName())
            .surname(xmlResponse.getSurname())
            .otherNames(xmlResponse.getOtherNames())
            .gender(xmlResponse.getGender())
            .email(xmlResponse.getEmail())
            .dateOfBirth(xmlResponse.getDateOfBirth())
            .placeOfBirth(xmlResponse.getPlaceOfBirth())
            .nationality(xmlResponse.getNationality())
            .phoneNumber(xmlResponse.getPhoneNumber())
            .maritalStatus(xmlResponse.getMaritalStatus())
            .photo(xmlResponse.getPhoto())
            .signature(xmlResponse.getSignature())
            .residentRegion(xmlResponse.getResidentRegion())
            .residentDistrict(xmlResponse.getResidentDistrict())
            .residentWard(xmlResponse.getResidentWard())
            .residentVillage(xmlResponse.getResidentVillage())
            .residentHouseNo(xmlResponse.getResidentHouseNo())
            .residentPostalAddress(xmlResponse.getResidentPostalAddress())
            .residentPostCode(xmlResponse.getResidentPostCode())
            .birthCountry(xmlResponse.getBirthCountry())
            .birthRegion(xmlResponse.getBirthRegion())
            .birthDistrict(xmlResponse.getBirthDistrict())
            .birthWard(xmlResponse.getBirthWard())
            .questionEn(xmlResponse.getQuestionEn())
            .questionSw(xmlResponse.getQuestionSw())
            .rqCode(xmlResponse.getRqCode())
            .previousAnswerCode(xmlResponse.getPreviousAnswerCode())
            .registeredFingerprints(xmlResponse.getRegisteredFingerprints())
            .build();
    }
}