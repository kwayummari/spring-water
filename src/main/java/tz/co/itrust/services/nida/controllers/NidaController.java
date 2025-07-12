package tz.co.itrust.services.nida.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import tz.co.itrust.services.nida.dto.request.BiometricVerificationRequestDto;
import tz.co.itrust.services.nida.dto.request.NidaVerificationRequestDto;
import tz.co.itrust.services.nida.dto.response.NidaVerificationResponseDto;
import tz.co.itrust.services.nida.service.NidaVerificationService;

import jakarta.validation.Valid;

/**
 * REST Controller for NIDA verification services
 * 
 */
@Slf4j
@RestController
@RequestMapping("/nida")
@RequiredArgsConstructor
@Validated
@Tag(name = "NIDA Verification", description = "APIs for Tanzania National ID verification")
public class NidaController {

    private final NidaVerificationService nidaVerificationService;

    @Operation(
        summary = "NIN Enquiry",
        description = "Performs NIN enquiry with optional security questionnaire"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Verification completed",
            content = @Content(schema = @Schema(implementation = NidaVerificationResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Invalid request data"
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "Unauthorized access"
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal server error"
        )
    })
    @PostMapping("/enquiry")
    // @PreAuthorize("hasRole('USER')")
    public ResponseEntity<NidaVerificationResponseDto> verifyNin(
            @Valid @RequestBody NidaVerificationRequestDto request) {
        
        log.info("Received NIN verification request for: {}", 
                request.getNin().substring(0, 4) + "****");
        
        NidaVerificationResponseDto response = nidaVerificationService.verifyWithQuestionnaire(request);
        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Biometric Enquiry",
        description = "Performs biometric enquiry using fingerprint data"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Biometric verification completed",
            content = @Content(schema = @Schema(implementation = NidaVerificationResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Invalid biometric data"
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "Unauthorized access"
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal server error"
        )
    })
    @PostMapping("/enquiry-bio")
    // @PreAuthorize("hasRole('USER')")
    public ResponseEntity<NidaVerificationResponseDto> verifyBiometric(
            @Valid @RequestBody BiometricVerificationRequestDto request) {
        
        log.info("Received biometric verification request for: {}", 
                request.getNin().substring(0, 4) + "****");
        
        NidaVerificationResponseDto response = nidaVerificationService.verifyWithBiometrics(request);
        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Health check",
        description = "Check if the NIDA service is operational"
    )
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("NIDA Service is operational");
    }
}