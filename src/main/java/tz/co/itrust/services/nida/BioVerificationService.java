package tz.co.itrust.services.nida;

import jakarta.xml.bind.*;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPHeaderElement;
import jakarta.xml.soap.SOAPMessage;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;
import tz.co.itrust.services.nida.datacontract.schemas.*;
import tz.co.itrust.services.nida.requests.BiometricRequest;
import tz.co.itrust.services.nida.responses.NidaResponse;
import tz.co.itrust.services.nida.tempuri.ObjectFactory;
import tz.co.itrust.services.nida.tempuri.*;
import javax.xml.namespace.QName;
import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class BioVerificationService extends WebServiceGatewaySupport {

	@Value("${base_url}")
	private String nidaNinUrl;
	@Value("${nida-id:1}")
	private String nidaId;
	@Value("${nida-user-id}")
	private String nidaUserId;
	@Value("${nida.request-timeout-sec:30}")
	private int nidaRequestTimeout;
	@Value("${nida.connect-timeout-sec:30}")
	private int nidaConnectTimeout;
	
	SecurityUtil securityUtil;

	private static String jaxbObjectToXML(BiometricRequest customer) {
		String xmlString = "";

		try {
			JAXBContext context = JAXBContext.newInstance(BiometricRequest.class);
			Marshaller m = context.createMarshaller();
			m.setProperty("jaxb.formatted.output", Boolean.TRUE);
			StringWriter sw = new StringWriter();
			m.marshal(customer, sw);
			xmlString = sw.toString();
		} catch (JAXBException var5) {
			var5.printStackTrace();
		}

		return xmlString;
	}

	public VerificationResponse inquiry(BiometricRequest request) {
		ObjectFactory tempuriObjFactory = new ObjectFactory();
		tz.co.itrust.services.nida.datacontract.schemas.ObjectFactory nidObjFactory = new tz.co.itrust.services.nida.datacontract.schemas.ObjectFactory();
		String nidaPayload = jaxbObjectToXML(request);
		this.logger.info("Generated payload --->> " + String.valueOf(request));
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("tz.co.itrust.services.nida.tempuri", "tz.co.itrust.services.nida.datacontract.schemas");
		this.setDefaultUri("https://nacer01/TZ_CIG/GatewayService.svc");
		this.setMarshaller(marshaller);
		this.setUnmarshaller(marshaller);

		// envelop
		BiometricVerification rqVerification = new BiometricVerification();

		// request
		JAXBElement<CIGRequest> jaxCigReq = tempuriObjFactory.createRQVerificationIRequest(new CIGRequest());

		// request header
		JAXBElement<RequestHeader> jaxHeader = nidObjFactory.createCIGRequestHeader(new RequestHeader());

		// request body
		JAXBElement<RequestBody> jaxBody = nidObjFactory.createCIGRequestBody(new RequestBody());

		String randomKey = securityUtil.generateRandomString(32);
		byte[] keyBytes = securityUtil.generateRijdaelKey(randomKey);
		String randomIV = securityUtil.generateRandomString(32);
		byte[] iv = securityUtil.generateRijdaelKey(randomIV);
		byte[] encPayload = securityUtil.encryptRijndael256(nidaPayload, keyBytes, iv);
		byte[] encKey = securityUtil.encryptRSA(keyBytes);
		byte[] encIV = securityUtil.encryptRSA(iv);
		byte[] payloadSig = securityUtil.signDigitally(encPayload);
		JAXBElement<RequestCryptoInfo> jaxRequestCryptoInfo = nidObjFactory.createRequestBodyCryptoInfo(new RequestCryptoInfo());
		jaxRequestCryptoInfo.getValue().setEncryptedCryptoIV(nidObjFactory.createRequestCryptoInfoEncryptedCryptoIV(new String(Base64.encode(encIV))));
		jaxRequestCryptoInfo.getValue().setEncryptedCryptoKey(nidObjFactory.createRequestCryptoInfoEncryptedCryptoKey(new String(Base64.encode(encKey))));
		jaxHeader.getValue().setClientNameOrIP(nidObjFactory.createRequestHeaderClientNameOrIP(""));
		jaxHeader.getValue().setId(nidObjFactory.createRequestHeaderId(this.nidaId));
		jaxHeader.getValue().setUserId(nidObjFactory.createRequestHeaderUserId(this.nidaUserId));
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		jaxHeader.getValue().setTimeStamp(nidObjFactory.createRequestHeaderTimeStamp(dateTimeFormat.format(LocalDateTime.now())));
		jaxBody.getValue().setCryptoInfo(jaxRequestCryptoInfo);
		jaxBody.getValue().setPayload(nidObjFactory.createRequestBodyPayload(new String(Base64.encode(encPayload))));
		jaxBody.getValue().setSignature(nidObjFactory.createRequestBodySignature(new String(Base64.encode(payloadSig))));
		jaxCigReq.getValue().setHeader(jaxHeader);
		jaxCigReq.getValue().setBody(jaxBody);
		rqVerification.setIRequest(jaxCigReq);
		this.logger.info("Finished preparing NIDA RQVerification request body, about to perform request...");

		try {
			MessageFactory msgFactory = MessageFactory.newInstance("SOAP 1.2 Protocol");
			SaajSoapMessageFactory saajSoapMessageFactory = new SaajSoapMessageFactory(msgFactory);
			WebServiceTemplate webServiceTemplate = this.getWebServiceTemplate();
			webServiceTemplate.setMessageFactory(saajSoapMessageFactory);
			WebServiceMessageSender[] var23 = webServiceTemplate.getMessageSenders();

            for (WebServiceMessageSender sender : var23) {
                try {
                    HttpUrlConnectionMessageSender httpSender = (HttpUrlConnectionMessageSender) sender;
                    httpSender.setConnectionTimeout(Duration.ofSeconds(this.nidaConnectTimeout));
                    httpSender.setReadTimeout(Duration.ofSeconds(this.nidaRequestTimeout));
                    this.logger.info("Setting timeouts in seconds, connect timeout " + this.nidaConnectTimeout + ", read timeout " + this.nidaRequestTimeout);
                } catch (NumberFormatException | ClassCastException var28) {
                    this.logger.warn("Cannot set timeout : " + var28.getMessage());
                }
            }

			this.logger.info("Attempting to send RQVerification request to NIDA");
			VerificationResponse rQVerificationResponse = (VerificationResponse) webServiceTemplate.marshalSendAndReceive(rqVerification, new WebServiceMessageCallback() {
				public void doWithMessage(WebServiceMessage message) {
					try {
						BioVerificationService.this.logger.info("Modifying webservice message to add Action and To headers");
						SOAPMessage soapMessage = ((SaajSoapMessage)message).getSaajMessage();
						SOAPHeader soapHeader = soapMessage.getSOAPHeader();
						SOAPHeaderElement actionElement = soapHeader.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "Action", "ns"));
						actionElement.setTextContent("http://tempuri.org/IGatewayService/RQVerification");
						SOAPHeaderElement toElement = soapHeader.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "To", "ns"));
						toElement.setTextContent("https://nacer01/TZ_CIG/GatewayService.svc");
						BioVerificationService.this.logger.info("Modifying webservice message to add Action and To headers");
					} catch (Exception var6) {
						BioVerificationService.this.logger.error("Error while modifying the webservice message " + var6.getMessage());
					}
				}
			});
			String code = rQVerificationResponse.getBiometricVerificationResult().getValue().getCode().getValue();
			this.logger.info("Response code from NIDA : " + code);
			this.logger.info("Response response from NIDA: " + ((CIGResponse) rQVerificationResponse.getBiometricVerificationResult().getValue()).getBody().getValue());
			return rQVerificationResponse;
		} catch (Exception var29) {
			this.logger.error("Got error while attempting NIDA request " + var29.getMessage());
			throw new RuntimeException(var29.getMessage());
		}
	}

	public NidaResponse decodeResponse(VerificationResponse verificationResp) {
		this.logger.info("Beginning decoding of nida BIO verification response");
		ResponseBody respBody = verificationResp.getBiometricVerificationResult().getValue().getBody().getValue();
		String b64Sig = respBody.getSignature().getValue();
		String b64Payload = respBody.getPayload().getValue();
		byte[] encPayload = Base64.decode(b64Payload);
		boolean verify = securityUtil.verifySignature(encPayload, Base64.decode(b64Sig));
		this.logger.info("Signature verify " + (verify ? "success" : "failed"));
		if (!verify) {
			throw new RuntimeException("Failed to verify payload signature");
		} else {
			this.logger.info("Decryption of Crypto key and Crytpo IV from nida");
			String b64IV = respBody.getCryptoInfo().getValue().getEncryptedCryptoIV().getValue();
			String b64Key = respBody.getCryptoInfo().getValue().getEncryptedCryptoKey().getValue();
			byte[] key = securityUtil.decryptRSA(Base64.decode(b64Key));
			byte[] iv = securityUtil.decryptRSA(Base64.decode(b64IV));
			this.logger.info("Decryption of nida response using rijndael algo with the obtained key and iv ");
			byte[] plainBytes = securityUtil.decryptRijndael256(encPayload, key, iv);
			String plainText = new String(plainBytes);
			this.logger.info("Decryption of nida RQVerificationResponse result -->> success");
			NidaResponse nidaResponse = null;
			try {
				this.logger.info("Unmarshalling plain xml response to NidaResponse object");
				int closeAnglePos = plainText.lastIndexOf(62);
				JAXBContext jaxbContext = JAXBContext.newInstance(new Class[]{NidaResponse.class});
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				StringReader reader = new StringReader(plainText.substring(0, closeAnglePos + 1));
				nidaResponse = (NidaResponse)unmarshaller.unmarshal(reader);
				reader.close();
			} catch (Exception var18) {
				this.logger.error("Failed to unmarshall the response into object, Error: " + var18.getMessage());
			}

			return nidaResponse;
		}
	}

}
