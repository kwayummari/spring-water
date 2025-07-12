package tz.co.itrust.services.nida.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Service;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import tz.co.itrust.services.nida.config.NidaProperties;
import tz.co.itrust.services.nida.dto.request.BiometricVerificationRequestDto;
import tz.co.itrust.services.nida.dto.request.NidaVerificationRequestDto;
import tz.co.itrust.services.nida.exceptions.SoapCommunicationException;
import tz.co.itrust.services.nida.security.CryptoService;
import tz.co.itrust.services.nida.soap.datacontract.*;
import tz.co.itrust.services.nida.soap.tempuri.*;
import tz.co.itrust.services.nida.soap.tempuri.ObjectFactory;
import tz.co.itrust.services.nida.soap.payload.NidaNINPayload;
import tz.co.itrust.services.nida.soap.payload.BiometricPayload;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPHeaderElement;
import jakarta.xml.soap.SOAPMessage;
import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * SOAP client for communicating with NIDA web services
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NidaWebServiceClient {

    private final WebServiceTemplate webServiceTemplate;
    private final CryptoService cryptoService;
    private final NidaProperties nidaProperties;

    /**
     * Perform NIDA verification request
     */
    public RQVerificationResponse performNidaVerification(NidaVerificationRequestDto request) {
        try {
            log.debug("Preparing NIDA verification request");
            
            // Create SOAP request objects
            ObjectFactory tempuriFactory = new ObjectFactory();
            tz.co.itrust.services.nida.soap.datacontract.ObjectFactory nidaFactory = 
                new tz.co.itrust.services.nida.soap.datacontract.ObjectFactory();

            // Convert DTO to XML payload
            String xmlPayload = createXmlPayload(request);
            
            // Encrypt payload
            EncryptedPayload encryptedPayload = encryptPayload(xmlPayload);
            
            // Build SOAP request
            RQVerification soapRequest = buildSoapRequest(tempuriFactory, nidaFactory, encryptedPayload);
            
            log.info("Sending NIDA verification request");
            
            // Send request with custom headers
            RQVerificationResponse response = (RQVerificationResponse) webServiceTemplate
                .marshalSendAndReceive(soapRequest, createSoapHeaderCallback());
            
            log.info("Received NIDA verification response");
            return response;
            
        } catch (Exception e) {
            log.error("NIDA verification request failed", e);
            throw new SoapCommunicationException("Failed to communicate with NIDA service", e);
        }
    }

    /**
     * Perform biometric verification request
     */
    public VerificationResponse performBiometricVerification(BiometricVerificationRequestDto request) {
        try {
            log.debug("Preparing biometric verification request");
            
            // Create SOAP request objects
            ObjectFactory tempuriFactory = new ObjectFactory();
            tz.co.itrust.services.nida.soap.datacontract.ObjectFactory nidaFactory = 
                new tz.co.itrust.services.nida.soap.datacontract.ObjectFactory();

            // Convert DTO to XML payload
            String xmlPayload = createBiometricXmlPayload(request);
            
            // Encrypt payload
            EncryptedPayload encryptedPayload = encryptPayload(xmlPayload);
            
            // Build SOAP request
            BiometricVerification soapRequest = buildBiometricSoapRequest(tempuriFactory, nidaFactory, encryptedPayload);
            
            log.info("Sending biometric verification request");
            
            // Send request with custom headers
            VerificationResponse response = (VerificationResponse) webServiceTemplate
                .marshalSendAndReceive(soapRequest, createSoapHeaderCallback());
            
            log.info("Received biometric verification response");
            return response;
            
        } catch (Exception e) {
            log.error("Biometric verification request failed", e);
            throw new SoapCommunicationException("Failed to communicate with NIDA biometric service", e);
        }
    }

    private String createXmlPayload(NidaVerificationRequestDto request) {
        try {
            // Create XML payload object
            NidaNINPayload payload = new NidaNINPayload();
            payload.setNin(request.getNin());
            payload.setRqCode(request.getRqCode());
            payload.setQnAnswer(request.getQnAnswer());
            
            // Marshal to XML
            JAXBContext context = JAXBContext.newInstance(NidaNINPayload.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            StringWriter writer = new StringWriter();
            marshaller.marshal(payload, writer);
            
            return writer.toString();
            
        } catch (Exception e) {
            throw new SoapCommunicationException("Failed to create XML payload", e);
        }
    }

    private String createBiometricXmlPayload(BiometricVerificationRequestDto request) {
        try {
            // Create XML payload object
            BiometricPayload payload = new BiometricPayload();
            payload.setNin(request.getNin());
            payload.setFingerCode(request.getFingerCode());
            payload.setFingerprintImage(request.getFingerprintImage());
            
            // Marshal to XML
            JAXBContext context = JAXBContext.newInstance(BiometricPayload.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            StringWriter writer = new StringWriter();
            marshaller.marshal(payload, writer);
            
            return writer.toString();
            
        } catch (Exception e) {
            throw new SoapCommunicationException("Failed to create biometric XML payload", e);
        }
    }

    private EncryptedPayload encryptPayload(String xmlPayload) {
        // Generate random key and IV
        String randomKey = cryptoService.generateRandomString(32);
        String randomIV = cryptoService.generateRandomString(32);
        
        byte[] keyBytes = cryptoService.generateRijndaelKey(randomKey);
        byte[] ivBytes = cryptoService.generateRijndaelKey(randomIV);
        
        // Encrypt payload
        byte[] encryptedPayload = cryptoService.encryptRijndael256(xmlPayload, keyBytes, ivBytes);
        
        // Encrypt key and IV with RSA
        byte[] encryptedKey = cryptoService.encryptRSA(keyBytes);
        byte[] encryptedIV = cryptoService.encryptRSA(ivBytes);
        
        // Sign payload
        byte[] signature = cryptoService.signDigitally(encryptedPayload);
        
        return EncryptedPayload.builder()
            .encryptedData(Base64.encode(encryptedPayload))
            .encryptedKey(Base64.encode(encryptedKey))
            .encryptedIV(Base64.encode(encryptedIV))
            .signature(Base64.encode(signature))
            .build();
    }

    private RQVerification buildSoapRequest(
            ObjectFactory tempuriFactory,
            tz.co.itrust.services.nida.soap.datacontract.ObjectFactory nidaFactory,
            EncryptedPayload encryptedPayload) {
        
        RQVerification request = new RQVerification();
        
        // Create request elements
        JAXBElement<CIGRequest> cigRequest = tempuriFactory.createRQVerificationIRequest(new CIGRequest());
        JAXBElement<RequestHeader> header = nidaFactory.createCIGRequestHeader(new RequestHeader());
        JAXBElement<RequestBody> body = nidaFactory.createCIGRequestBody(new RequestBody());
        
        // Set header information
        RequestHeader headerValue = header.getValue();
        headerValue.setClientNameOrIP(nidaFactory.createRequestHeaderClientNameOrIP(""));
        headerValue.setId(nidaFactory.createRequestHeaderId(nidaProperties.getService().getId()));
        headerValue.setUserId(nidaFactory.createRequestHeaderUserId(nidaProperties.getService().getUserId()));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        headerValue.setTimeStamp(nidaFactory.createRequestHeaderTimeStamp(
            formatter.format(LocalDateTime.now())));
        
        // Set body information
        RequestBody bodyValue = body.getValue();
        
        // Create crypto info
        JAXBElement<RequestCryptoInfo> cryptoInfo = nidaFactory.createRequestBodyCryptoInfo(new RequestCryptoInfo());
        RequestCryptoInfo cryptoInfoValue = cryptoInfo.getValue();
        cryptoInfoValue.setEncryptedCryptoKey(nidaFactory.createRequestCryptoInfoEncryptedCryptoKey(
            new String(encryptedPayload.getEncryptedKey())));
        cryptoInfoValue.setEncryptedCryptoIV(nidaFactory.createRequestCryptoInfoEncryptedCryptoIV(
            new String(encryptedPayload.getEncryptedIV())));
        
        bodyValue.setCryptoInfo(cryptoInfo);
        bodyValue.setPayload(nidaFactory.createRequestBodyPayload(new String(encryptedPayload.getEncryptedData())));
        bodyValue.setSignature(nidaFactory.createRequestBodySignature(new String(encryptedPayload.getSignature())));
        
        // Assemble request
        CIGRequest cigRequestValue = cigRequest.getValue();
        cigRequestValue.setHeader(header);
        cigRequestValue.setBody(body);
        
        request.setIRequest(cigRequest);
        
        return request;
    }

    private BiometricVerification buildBiometricSoapRequest(
            ObjectFactory tempuriFactory,
            tz.co.itrust.services.nida.soap.datacontract.ObjectFactory nidaFactory,
            EncryptedPayload encryptedPayload) {
        
        // Similar to buildSoapRequest but for biometric verification
        BiometricVerification request = new BiometricVerification();
        
        // Implementation similar to buildSoapRequest
        // ... (implementation details)
        
        return request;
    }

    private WebServiceMessageCallback createSoapHeaderCallback() {
        return new WebServiceMessageCallback() {
            @Override
            public void doWithMessage(WebServiceMessage message) {
                try {
                    SaajSoapMessage soapMessage = (SaajSoapMessage) message;
                    SOAPMessage soap = soapMessage.getSaajMessage();
                    SOAPHeader header = soap.getSOAPHeader();
                    
                    // Add Action header
                    SOAPHeaderElement actionElement = header.addHeaderElement(
                        new QName("http://www.w3.org/2005/08/addressing", "Action", "ns"));
                    actionElement.setTextContent("http://tempuri.org/IGatewayService/RQVerification");
                    
                    // Add To header
                    SOAPHeaderElement toElement = header.addHeaderElement(
                        new QName("http://www.w3.org/2005/08/addressing", "To", "ns"));
                    toElement.setTextContent(nidaProperties.getService().getBaseUrl());
                    
                    log.debug("Added SOAP headers for NIDA request");
                    
                } catch (Exception e) {
                    log.error("Failed to add SOAP headers", e);
                    throw new SoapCommunicationException("Failed to configure SOAP headers", e);
                }
            }
        };
    }

    /**
     * Helper class for encrypted payload data
     */
    private static class EncryptedPayload {
        private final byte[] encryptedData;
        private final byte[] encryptedKey;
        private final byte[] encryptedIV;
        private final byte[] signature;
        
        private EncryptedPayload(Builder builder) {
            this.encryptedData = builder.encryptedData;
            this.encryptedKey = builder.encryptedKey;
            this.encryptedIV = builder.encryptedIV;
            this.signature = builder.signature;
        }
        
        public static Builder builder() {
            return new Builder();
        }
        
        public byte[] getEncryptedData() { return encryptedData; }
        public byte[] getEncryptedKey() { return encryptedKey; }
        public byte[] getEncryptedIV() { return encryptedIV; }
        public byte[] getSignature() { return signature; }
        
        public static class Builder {
            private byte[] encryptedData;
            private byte[] encryptedKey;
            private byte[] encryptedIV;
            private byte[] signature;
            
            public Builder encryptedData(byte[] encryptedData) {
                this.encryptedData = encryptedData;
                return this;
            }
            
            public Builder encryptedKey(byte[] encryptedKey) {
                this.encryptedKey = encryptedKey;
                return this;
            }
            
            public Builder encryptedIV(byte[] encryptedIV) {
                this.encryptedIV = encryptedIV;
                return this;
            }
            
            public Builder signature(byte[] signature) {
                this.signature = signature;
                return this;
            }
            
            public EncryptedPayload build() {
                return new EncryptedPayload(this);
            }
        }
    }
}