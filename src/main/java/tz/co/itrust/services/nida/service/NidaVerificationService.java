package tz.co.itrust.services.nida.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import tz.co.itrust.services.nida.dto.request.BiometricVerificationRequestDto;
import tz.co.itrust.services.nida.dto.request.NidaVerificationRequestDto;
import tz.co.itrust.services.nida.dto.response.NidaVerificationResponseDto;
import tz.co.itrust.services.nida.exceptions.NidaServiceException;
import tz.co.itrust.services.nida.soap.tempuri.RQVerificationResponse;
import tz.co.itrust.services.nida.soap.tempuri.VerificationResponse;
import tz.co.itrust.services.nida.util.Constants;
import tz.co.itrust.services.nida.soap.datacontract.CIGResponse;

/**
 * Main service for NIDA verification operations
 * 
 * Provides high-level methods for:
 * - Standard NIN verification with questionnaire
 * - Biometric verification
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NidaVerificationService {

    private final NidaWebServiceClient webServiceClient;
    private final ResponseProcessingService responseProcessingService;

    /**
     * Perform NIDA verification with optional questionnaire
     */
    public NidaVerificationResponseDto verifyWithQuestionnaire(NidaVerificationRequestDto request) {
        log.info("Starting NIDA verification for NIN: {}", 
                request.getNin().substring(0, 4) + "****");
        
        try {
            // Call NIDA web service
            RQVerificationResponse soapResponse = webServiceClient.performNidaVerification(request);
            
            // Process and decrypt response
            NidaVerificationResponseDto response = responseProcessingService.processVerificationResponse(soapResponse);
            
            // Determine verification status
            response.setVerificationStatus(determineVerificationStatus(soapResponse, response));
            response.setMessage(generateVerificationMessage(response));
            
            log.info("NIDA verification completed for NIN: {}", 
                    request.getNin().substring(0, 4) + "****");
            
            return response;
            
        } catch (Exception e) {
            log.error("NIDA verification failed for NIN: {}", 
                     request.getNin().substring(0, 4) + "****", e);
            throw new NidaServiceException("Verification failed", e);
        }
    }

    /**
     * Perform biometric verification
     */
    public NidaVerificationResponseDto verifyWithBiometrics(BiometricVerificationRequestDto request) {
        log.info("Starting biometric verification for NIN: {}", 
                request.getNin().substring(0, 4) + "****");
        
        try {
            // Call NIDA biometric service
            VerificationResponse soapResponse = webServiceClient.performBiometricVerification(request);
            
            // Process and decrypt response
            NidaVerificationResponseDto response = responseProcessingService.processBiometricResponse(soapResponse);
            
            // Determine verification status
            response.setVerificationStatus(determineVerificationStatus(soapResponse, response));
            response.setMessage(generateVerificationMessage(response));
            
            log.info("Biometric verification completed for NIN: {}", 
                    request.getNin().substring(0, 4) + "****");
            
            return response;
            
        } catch (Exception e) {
            log.error("Biometric verification failed for NIN: {}", 
                     request.getNin().substring(0, 4) + "****", e);
            throw new NidaServiceException("Biometric verification failed", e);
        }
    }

    private NidaVerificationResponseDto.VerificationStatus determineVerificationStatus(
            Object soapResponse, NidaVerificationResponseDto response) {
        
        // Extract response code from SOAP response
        String responseCode = extractResponseCode(soapResponse);
        
        return switch (responseCode) {
            case Constants.ResponseCodes.SUCCESS -> NidaVerificationResponseDto.VerificationStatus.SUCCESS;
            case Constants.ResponseCodes.PENDING_QUESTION -> NidaVerificationResponseDto.VerificationStatus.PENDING_QUESTION;
            case Constants.ResponseCodes.NIN_NOT_FOUND -> NidaVerificationResponseDto.VerificationStatus.NIN_NOT_FOUND;
            case Constants.ResponseCodes.VERIFICATION_FAILED, 
                 Constants.ResponseCodes.GENERAL_FAILURE, 
                 Constants.ResponseCodes.SYSTEM_ERROR -> NidaVerificationResponseDto.VerificationStatus.FAILED;
            case Constants.ResponseCodes.WRONG_ANSWER -> NidaVerificationResponseDto.VerificationStatus.INVALID_ANSWER;
            default -> NidaVerificationResponseDto.VerificationStatus.FAILED;
        };
    }

    private String generateVerificationMessage(NidaVerificationResponseDto response) {
        return switch (response.getVerificationStatus()) {
            case SUCCESS -> "Verification completed successfully";
            case PENDING_QUESTION -> "Please answer the security question";
            case NIN_NOT_FOUND -> "National ID not found in NIDA database";
            case INVALID_ANSWER -> "Invalid answer to security question";
            case FAILED -> "Verification failed";
        };
    }

    private String extractResponseCode(Object soapResponse) {
        try {
            if (soapResponse instanceof RQVerificationResponse) {
                RQVerificationResponse response = (RQVerificationResponse) soapResponse;
                CIGResponse cigResponse = response.getRQVerificationResult().getValue();
                return cigResponse.getCode().getValue();
            } else if (soapResponse instanceof VerificationResponse) {
                VerificationResponse response = (VerificationResponse) soapResponse;
                CIGResponse cigResponse = response.getBiometricVerificationResult().getValue();
                return cigResponse.getCode().getValue();
            }
        } catch (Exception e) {
            log.error("Failed to extract response code from SOAP response", e);
        }
        
        // Default to failed if we can't extract the code
        return Constants.ResponseCodes.GENERAL_FAILURE;
    }
}