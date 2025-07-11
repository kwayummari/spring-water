package tz.co.itrust.services.nida;

import tz.co.itrust.services.nida.datacontract.schemas.CIGRequest;
import tz.co.itrust.services.nida.datacontract.schemas.CIGResponse;
import tz.co.itrust.services.nida.datacontract.schemas.RequestBody;
import tz.co.itrust.services.nida.datacontract.schemas.RequestCryptoInfo;
import tz.co.itrust.services.nida.datacontract.schemas.RequestHeader;
import tz.co.itrust.services.nida.datacontract.schemas.ResponseBody;
import tz.co.itrust.services.nida.datacontract.schemas.ResponseCryptoInfo;
import tz.co.itrust.services.nida.requests.BiometricRequest;
import tz.co.itrust.services.nida.requests.NidaNINPayload;
import tz.co.itrust.services.nida.responses.NidaResponse;
import tz.co.itrust.services.nida.tempuri.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPHeaderElement;
import jakarta.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Security;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.Validate;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.engines.RijndaelEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.signers.RSADigestSigner;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.util.Arrays;
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

@Service
public class NIDAVerificationService extends WebServiceGatewaySupport {
	@Value("${base_url}")
	private String nidaNinUrl;
	@Value("${nida-id:1}")
	private String nidaId;
	@Value("${nida-user-id}")
	private String nidaUserId;
	@Value("${nida.public-key-path}")
	private String nidaPubKeyPath;
	@Value("${nida-private-key-path}")
	private String itrustPrivKeyPath;
	@Value("${nida.request-timeout-sec:30}")
	private int nidaRequestTimeout;
	@Value("${nida.connect-timeout-sec:30}")
	private int nidaConnectTimeout;

	private static String jaxbObjectToXMLBio(BiometricRequest customer) {
		String xmlString = "";

		try {
			JAXBContext context = JAXBContext.newInstance(new Class[]{BiometricRequest.class});
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

	private static String jaxbObjectToXML(NidaNINPayload customer) {
		String xmlString = "";

		try {
			JAXBContext context = JAXBContext.newInstance(new Class[]{NidaNINPayload.class});
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

	public RQVerificationResponse nidaInquiry(NidaNINPayload request) {
		ObjectFactory tempuriObjFactory = new ObjectFactory();
		tz.co.itrust.services.nida.datacontract.schemas.ObjectFactory nidObjFactory = new tz.co.itrust.services.nida.datacontract.schemas.ObjectFactory();
		String nidaPayload = jaxbObjectToXML(request);
		this.logger.info("Generated payload --->> " + String.valueOf(request));
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(new String[]{"tz.co.itrust.services.nida.tempuri", "tz.co.itrust.services.nida.datacontract.schemas"});
		this.setDefaultUri("https://nacer01/TZ_CIG/GatewayService.svc");
		this.setMarshaller(marshaller);
		this.setUnmarshaller(marshaller);

		// envelop
		RQVerification rqVerification = new RQVerification();

		// request
		JAXBElement<CIGRequest> jaxCigReq = tempuriObjFactory.createRQVerificationIRequest(new CIGRequest());

		// request header
		JAXBElement<RequestHeader> jaxHeader = nidObjFactory.createCIGRequestHeader(new RequestHeader());

		// request body
		JAXBElement<RequestBody> jaxBody = nidObjFactory.createCIGRequestBody(new RequestBody());

		String randomKey = generateRandomString(32);
		byte[] keyBytes = this.generateRijdaelKey(randomKey);
		String randomIV = generateRandomString(32);
		byte[] iv = this.generateRijdaelKey(randomIV);
		byte[] encPayload = this.encryptRijndael256(nidaPayload, keyBytes, iv);
//		this.nidaPubKeyPath = ((URL)Objects.requireNonNull(this.getClass().getClassLoader().getResource("NIDACIGSecurity.pem"))).getFile();
		byte[] encKey = this.encryptRSA(keyBytes);
		byte[] encIV = this.encryptRSA(iv);
//		this.itrustPrivKeyPath = ((URL)Objects.requireNonNull(this.getClass().getClassLoader().getResource("IMAAN.key"))).getFile();
		byte[] payloadSig = this.signDigitally(encPayload);
		JAXBElement<RequestCryptoInfo> jaxRequestCryptoInfo = nidObjFactory.createRequestBodyCryptoInfo(new RequestCryptoInfo());
		((RequestCryptoInfo)jaxRequestCryptoInfo.getValue()).setEncryptedCryptoIV(nidObjFactory.createRequestCryptoInfoEncryptedCryptoIV(new String(Base64.encode(encIV))));
		((RequestCryptoInfo)jaxRequestCryptoInfo.getValue()).setEncryptedCryptoKey(nidObjFactory.createRequestCryptoInfoEncryptedCryptoKey(new String(Base64.encode(encKey))));
		((RequestHeader)jaxHeader.getValue()).setClientNameOrIP(nidObjFactory.createRequestHeaderClientNameOrIP(""));
		((RequestHeader)jaxHeader.getValue()).setId(nidObjFactory.createRequestHeaderId(this.nidaId));
		((RequestHeader)jaxHeader.getValue()).setUserId(nidObjFactory.createRequestHeaderUserId(this.nidaUserId));
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		((RequestHeader)jaxHeader.getValue()).setTimeStamp(nidObjFactory.createRequestHeaderTimeStamp(dateTimeFormat.format(LocalDateTime.now())));
		((RequestBody)jaxBody.getValue()).setCryptoInfo(jaxRequestCryptoInfo);
		((RequestBody)jaxBody.getValue()).setPayload(nidObjFactory.createRequestBodyPayload(new String(Base64.encode(encPayload))));
		((RequestBody)jaxBody.getValue()).setSignature(nidObjFactory.createRequestBodySignature(new String(Base64.encode(payloadSig))));
		((CIGRequest)jaxCigReq.getValue()).setHeader(jaxHeader);
		((CIGRequest)jaxCigReq.getValue()).setBody(jaxBody);
		rqVerification.setIRequest(jaxCigReq);
		this.logger.info("Finished preparing NIDA RQVerification request body, about to perform request...");

		try {
			MessageFactory msgFactory = MessageFactory.newInstance("SOAP 1.2 Protocol");
			SaajSoapMessageFactory saajSoapMessageFactory = new SaajSoapMessageFactory(msgFactory);
			WebServiceTemplate webServiceTemplate = this.getWebServiceTemplate();
			webServiceTemplate.setMessageFactory(saajSoapMessageFactory);
			WebServiceMessageSender[] var23 = webServiceTemplate.getMessageSenders();
			int var24 = var23.length;

			for(int var25 = 0; var25 < var24; ++var25) {
				WebServiceMessageSender sender = var23[var25];

				try {
					HttpUrlConnectionMessageSender httpSender = (HttpUrlConnectionMessageSender)sender;
					httpSender.setConnectionTimeout(Duration.ofSeconds((long)this.nidaConnectTimeout));
					httpSender.setReadTimeout(Duration.ofSeconds((long)this.nidaRequestTimeout));
					this.logger.info("Setting timeouts in seconds, connect timeout " + this.nidaConnectTimeout + ", read timeout " + this.nidaRequestTimeout);
				} catch (NumberFormatException | ClassCastException var28) {
					var28.printStackTrace();
					this.logger.warn("Cannot set timeout : " + var28.getMessage());
				}
			}

			this.logger.info("Attempting to send RQVerification request to NIDA");
			RQVerificationResponse rQVerificationResponse = (RQVerificationResponse) webServiceTemplate.marshalSendAndReceive(rqVerification, new WebServiceMessageCallback() {
				public void doWithMessage(WebServiceMessage message) {
					try {
						NIDAVerificationService.this.logger.info("Modifying webservice message to add Action and To headers");
						SOAPMessage soapMessage = ((SaajSoapMessage)message).getSaajMessage();
						SOAPHeader soapHeader = soapMessage.getSOAPHeader();
						SOAPHeaderElement actionElement = soapHeader.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "Action", "ns"));
						actionElement.setTextContent("http://tempuri.org/IGatewayService/RQVerification");
						SOAPHeaderElement toElement = soapHeader.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "To", "ns"));
						toElement.setTextContent("https://nacer01/TZ_CIG/GatewayService.svc");
						NIDAVerificationService.this.logger.info("Modifying webservice message to add Action and To headers");
					} catch (Exception var6) {
						NIDAVerificationService.this.logger.error("Error while modifying the webservice message " + var6.getMessage());
						var6.printStackTrace();
					}
				}
			});
			String code = (String)((CIGResponse)rQVerificationResponse.getRQVerificationResult().getValue()).getCode().getValue();
			this.logger.info("Response code from NIDA : " + code);
			this.logger.info("Response response from NIDA: " + String.valueOf(((CIGResponse)rQVerificationResponse.getRQVerificationResult().getValue()).getBody().getValue()));
			return rQVerificationResponse;
		} catch (Exception var29) {
			this.logger.error("Got error while attempting NIDA request " + var29.getMessage());
			var29.printStackTrace();
			throw new RuntimeException(var29.getMessage());
		}
	}

	public VerificationResponse bioInquiry(BiometricRequest request) {
		ObjectFactory tempuriObjFactory = new ObjectFactory();
		tz.co.itrust.services.nida.datacontract.schemas.ObjectFactory nidObjFactory = new tz.co.itrust.services.nida.datacontract.schemas.ObjectFactory();
		String nidaPayload = jaxbObjectToXMLBio(request);
		this.logger.info("Generated payload --->> " + String.valueOf(request));
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(new String[]{"tz.co.itrust.services.nida.tempuri", "tz.co.itrust.services.nida.datacontract.schemas"});
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

		String randomKey = generateRandomString(32);
		byte[] keyBytes = this.generateRijdaelKey(randomKey);
		String randomIV = generateRandomString(32);
		byte[] iv = this.generateRijdaelKey(randomIV);
		byte[] encPayload = this.encryptRijndael256(nidaPayload, keyBytes, iv);
//		this.nidaPubKeyPath = ((URL)Objects.requireNonNull(this.getClass().getClassLoader().getResource("NIDACIGSecurity.pem"))).getFile();
		byte[] encKey = this.encryptRSA(keyBytes);
		byte[] encIV = this.encryptRSA(iv);
//		this.itrustPrivKeyPath = ((URL)Objects.requireNonNull(this.getClass().getClassLoader().getResource("IMAAN.key"))).getFile();
		byte[] payloadSig = this.signDigitally(encPayload);
		JAXBElement<RequestCryptoInfo> jaxRequestCryptoInfo = nidObjFactory.createRequestBodyCryptoInfo(new RequestCryptoInfo());
		((RequestCryptoInfo)jaxRequestCryptoInfo.getValue()).setEncryptedCryptoIV(nidObjFactory.createRequestCryptoInfoEncryptedCryptoIV(new String(Base64.encode(encIV))));
		((RequestCryptoInfo)jaxRequestCryptoInfo.getValue()).setEncryptedCryptoKey(nidObjFactory.createRequestCryptoInfoEncryptedCryptoKey(new String(Base64.encode(encKey))));
		((RequestHeader)jaxHeader.getValue()).setClientNameOrIP(nidObjFactory.createRequestHeaderClientNameOrIP(""));
		((RequestHeader)jaxHeader.getValue()).setId(nidObjFactory.createRequestHeaderId(this.nidaId));
		((RequestHeader)jaxHeader.getValue()).setUserId(nidObjFactory.createRequestHeaderUserId(this.nidaUserId));
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		((RequestHeader)jaxHeader.getValue()).setTimeStamp(nidObjFactory.createRequestHeaderTimeStamp(dateTimeFormat.format(LocalDateTime.now())));
		((RequestBody)jaxBody.getValue()).setCryptoInfo(jaxRequestCryptoInfo);
		((RequestBody)jaxBody.getValue()).setPayload(nidObjFactory.createRequestBodyPayload(new String(Base64.encode(encPayload))));
		((RequestBody)jaxBody.getValue()).setSignature(nidObjFactory.createRequestBodySignature(new String(Base64.encode(payloadSig))));
		((CIGRequest)jaxCigReq.getValue()).setHeader(jaxHeader);
		((CIGRequest)jaxCigReq.getValue()).setBody(jaxBody);
		rqVerification.setIRequest(jaxCigReq);
		this.logger.info("Finished preparing NIDA RQVerification request body, about to perform request...");

		try {
			MessageFactory msgFactory = MessageFactory.newInstance("SOAP 1.2 Protocol");
			SaajSoapMessageFactory saajSoapMessageFactory = new SaajSoapMessageFactory(msgFactory);
			WebServiceTemplate webServiceTemplate = this.getWebServiceTemplate();
			webServiceTemplate.setMessageFactory(saajSoapMessageFactory);
			WebServiceMessageSender[] var23 = webServiceTemplate.getMessageSenders();
			int var24 = var23.length;

			for(int var25 = 0; var25 < var24; ++var25) {
				WebServiceMessageSender sender = var23[var25];

				try {
					HttpUrlConnectionMessageSender httpSender = (HttpUrlConnectionMessageSender)sender;
					httpSender.setConnectionTimeout(Duration.ofSeconds((long)this.nidaConnectTimeout));
					httpSender.setReadTimeout(Duration.ofSeconds((long)this.nidaRequestTimeout));
					this.logger.info("Setting timeouts in seconds, connect timeout " + this.nidaConnectTimeout + ", read timeout " + this.nidaRequestTimeout);
				} catch (NumberFormatException | ClassCastException var28) {
					var28.printStackTrace();
					this.logger.warn("Cannot set timeout : " + var28.getMessage());
				}
			}

			this.logger.info("Attempting to send RQVerification request to NIDA");
			VerificationResponse rQVerificationResponse = (VerificationResponse) webServiceTemplate.marshalSendAndReceive(rqVerification, new WebServiceMessageCallback() {
				public void doWithMessage(WebServiceMessage message) {
					try {
						NIDAVerificationService.this.logger.info("Modifying webservice message to add Action and To headers");
						SOAPMessage soapMessage = ((SaajSoapMessage)message).getSaajMessage();
						SOAPHeader soapHeader = soapMessage.getSOAPHeader();
						SOAPHeaderElement actionElement = soapHeader.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "Action", "ns"));
						actionElement.setTextContent("http://tempuri.org/IGatewayService/RQVerification");
						SOAPHeaderElement toElement = soapHeader.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "To", "ns"));
						toElement.setTextContent("https://nacer01/TZ_CIG/GatewayService.svc");
						NIDAVerificationService.this.logger.info("Modifying webservice message to add Action and To headers");
					} catch (Exception var6) {
						NIDAVerificationService.this.logger.error("Error while modifying the webservice message " + var6.getMessage());
						var6.printStackTrace();
					}
				}
			});
			String code = (String)((CIGResponse)rQVerificationResponse.getBiometricVerificationResult().getValue()).getCode().getValue();
			this.logger.info("Response code from NIDA : " + code);
			this.logger.info("Response response from NIDA: " + String.valueOf(((CIGResponse)rQVerificationResponse.getBiometricVerificationResult().getValue()).getBody().getValue()));
			return rQVerificationResponse;
		} catch (Exception var29) {
			this.logger.error("Got error while attempting NIDA request " + var29.getMessage());
			var29.printStackTrace();
			throw new RuntimeException(var29.getMessage());
		}
	}

	public NidaResponse decodeNidaResponse(RQVerificationResponse rQVerificationResp) {
		this.logger.info("Beginning decoding of nida RQ verification response");
		ResponseBody respBody = (ResponseBody)((CIGResponse)rQVerificationResp.getRQVerificationResult().getValue()).getBody().getValue();
		String b64Sig = (String)respBody.getSignature().getValue();
		String b64Payload = (String)respBody.getPayload().getValue();
		byte[] encPayload = Base64.decode(b64Payload);
		boolean verify = this.verifySignature(encPayload, Base64.decode(b64Sig));
		this.logger.info("Signature verify " + (verify ? "success" : "failed"));
		if (!verify) {
			throw new RuntimeException("Failed to verify payload signature");
		} else {
			this.logger.info("Decryption of Crypto key and Crytpo IV from nida");
			String b64IV = (String)((ResponseCryptoInfo)respBody.getCryptoInfo().getValue()).getEncryptedCryptoIV().getValue();
			String b64Key = (String)((ResponseCryptoInfo)respBody.getCryptoInfo().getValue()).getEncryptedCryptoKey().getValue();
			byte[] key = this.decryptRSA(Base64.decode(b64Key), this.itrustPrivKeyPath);
			byte[] iv = this.decryptRSA(Base64.decode(b64IV), this.itrustPrivKeyPath);
			this.logger.info("Decryption of nida response using rijndael algo with the obtained key and iv ");
			byte[] plainBytes = this.decryptRijndael256(encPayload, key, iv);
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
				var18.printStackTrace();
			}

			return nidaResponse;
		}
	}

	public NidaResponse decodeBioResponse(VerificationResponse rQVerificationResp) {
		this.logger.info("Beginning decoding of nida RQ verification response");
		ResponseBody respBody = (ResponseBody)((CIGResponse)rQVerificationResp.getBiometricVerificationResult().getValue()).getBody().getValue();
		String b64Sig = (String)respBody.getSignature().getValue();
		String b64Payload = (String)respBody.getPayload().getValue();
		byte[] encPayload = Base64.decode(b64Payload);
		boolean verify = this.verifySignature(encPayload, Base64.decode(b64Sig));
		this.logger.info("Signature verify " + (verify ? "success" : "failed"));
		if (!verify) {
			throw new RuntimeException("Failed to verify payload signature");
		} else {
			this.logger.info("Decryption of Crypto key and Crytpo IV from nida");
			String b64IV = (String)((ResponseCryptoInfo)respBody.getCryptoInfo().getValue()).getEncryptedCryptoIV().getValue();
			String b64Key = (String)((ResponseCryptoInfo)respBody.getCryptoInfo().getValue()).getEncryptedCryptoKey().getValue();
			byte[] key = this.decryptRSA(Base64.decode(b64Key), this.itrustPrivKeyPath);
			byte[] iv = this.decryptRSA(Base64.decode(b64IV), this.itrustPrivKeyPath);
			this.logger.info("Decryption of nida response using rijndael algo with the obtained key and iv ");
			byte[] plainBytes = this.decryptRijndael256(encPayload, key, iv);
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
				var18.printStackTrace();
			}

			return nidaResponse;
		}
	}

	private byte[] decryptRSA(byte[] encData, String keyFilePath) {
		byte[] hexEncodedCipher = null;

		try {
			Security.addProvider(new BouncyCastleProvider());
			String initialString = "-----BEGIN RSA PRIVATE KEY-----\nMIIEowIBAAKCAQEAvB7FPcxwD1cNztQAj1BEiW+SVEqDMnlsglQRyCfqtPPFm8PD\nuKoGv3VAEkjd1hpkr7acrF8IgBH3HhH8S/qt00sBn160FkT5ps8aywKXwGl3oK/f\nJ1dLC1U/FmidZBwLxIuvO9+W3hKOFH6Qe6tb7GsrSg3FvrxRa2s5/lrJ/XAae6Bh\ne3s2jVq3hMcE1iQg0EQFGOYlQq482VKj+nel0UB5ZkfcLDatmavVae23RrTut1ul\noCrHlf1Y2m42UJKsEfJFDvrJdNr5bs/8fxN7iWN9HgQSbcIjntW1lFo3sPuEu1mP\n6k7mrrTxyPC3Rq0p6yP8JWLTcCwX6NbG6hgWkwIDAQABAoIBACepMRpzVJjXWLDn\nX2rXmGuFW0tsHjbxBKCBivIxADnAP0Q0xLnVFvEcjjKMrEBsdhfZnzR0IL2ni/sJ\n/bAjHKy0XpY0CDG9vwidF1//nvDRC4iUK68Gp2qvQUq/KZemlhCNBpfP7uXI8a1+\ndYWtPgcdHRZSywMIfv3vsF1g1L8am5PS5jAVdmhIfPFDMx06S0GT1vK4T9jbi5yx\n9QGVaDlE8INsIz59fhfl1VYP6naFGYYCULLS3U41jZNXMP8whESVYfOHYkQI4lqU\ngBS4WIYAGYZV/VCu5pj8tZfLnS1TSR+knqUamRwYSPKf2CdGKwxT6sDOkwCBinf6\nS5wFRKkCgYEA7gXH7ftxL7aLaPSMTEFSznwXb+eZONYuVi4+GNSaqTgqRG6VnQu6\n3ZuulBxxgwADe7XlgOIX89Vp+oVsEvuXnZ+JTQK0eN7KBJesP2nUWwcXxpT/Dvpe\ndO3QynhiXaXKPwsbStX/lEm0e+VAh7WX/MZRbI09ITeuCyCGaFdZwl8CgYEAylQd\nq9NF4ZpMsxT164GwdnErJXo9cTXMSGrCZXh9cSA+qEdMcSP9g/cGLmM7dT7TsCCS\npRUpq28l7Zw74htP9hlyDmTsqX5r/sy/hwh0Vai6xy8J388isyil7wJglyi8Y9Du\nZAarN142mSZRgymUdQEesMhsxdJbRgYqGTLrYE0CgYEAnLkRu5EkJdZ8VM8w0cTx\nUSREClrkeJgOSpCIMrFKZMGmkwh9Wrquf9xSRxOzah5ILNzIEOTOAK806M+RKa6f\nVhoFvb9aNXv8aKm1sMtuF8HD1e2lQ6d3KmasY6SAoEjZskkN32iosGsXe7ynkLPx\nL28ljeQxq/2Ni6YO2gUqBOECgYBiPgHCQu31o4SGmDDoz6oteLnokrhu1h6BM0V9\nG8pdObjy4NvfPAiHIVUhBRID1iPXq5lJC0OWeHvEKk5xda2X47ccAilgC4DI0gZV\nvOcwCKPYv6BdyighMiWQLmlUPHVtSi/W5d4RHnAIYTDHLVNmlMqgMFGQpJmpIKkk\nXzMnKQKBgBifqdcgxtlI8hzdoZGfVWYuoDN9hrVTz7EKmISCtlXHY0y5LlFIDPsv\n5B+d8XT5k/Z6A07o7amimZuTDvha5jLU1AmUZ9NhqTkmmdTRzghR5vg5Zletjcdd\n6oWWqwOYCk3zhhSiGuKLeJIX7syN/scyUq1drDNMHEsyU0OeWJ8D\n-----END RSA PRIVATE KEY-----\n";
			InputStream fStream = new ByteArrayInputStream(initialString.getBytes());
			AsymmetricKeyParameter privateKey = NIDAVerificationService.KeyUtil.loadPrivateKey(fStream);
			fStream.close();
			AsymmetricBlockCipher ei = new RSAEngine();
			AsymmetricBlockCipher e = new PKCS1Encoding(ei);
			e.init(false, privateKey);
			hexEncodedCipher = e.processBlock(encData, 0, encData.length);
		} catch (Exception var8) {
			System.out.println(var8);
			var8.printStackTrace();
		}

		return hexEncodedCipher;
	}

	public byte[] encryptRSA(byte[] data) {
		byte[] hexEncodedCipher = null;

		try {
			Security.addProvider(new BouncyCastleProvider());
			String initialString = "-----BEGIN CERTIFICATE-----\n" +
					"MIIF2jCCBMKgAwIBAgITXwAAAfB7mshObgOoZgAAAAAB8DANBgkqhkiG9w0BAQUF\n" +
					"ADBTMRIwEAYKCZImiZPyLGQBGRYCdHoxEjAQBgoJkiaJk/IsZAEZFgJnbzEVMBMG\n" +
					"CgmSJomT8ixkARkWBW5pZHJuMRIwEAYDVQQDEwlOSURBU3ViQ0EwHhcNMjQxMDAz\n" +
					"MDkxNTIyWhcNMjYxMDAzMDkxNTIyWjCBpDELMAkGA1UEBhMCVFoxDDAKBgNVBAgT\n" +
					"A0RTTTEMMAoGA1UEBxMDRFNNMTAwLgYDVQQKEydOQVRJT05BTCBJREVOVElGSUNB\n" +
					"VElPTiBBVVRIT1JJVFkoTklEQSkxDTALBgNVBAsTBE5JREExGDAWBgNVBAMTD05J\n" +
					"REFDSUdTZWN1cml0eTEeMBwGCSqGSIb3DQEJARYPaW5mb0BuaWRhLmdvLnR6MIIB\n" +
					"IjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmkFvbONSZ//kTxL5CGtV01oI\n" +
					"5PHn8DAq5zQN5U8GTL9VjH6jGA7OXIhvZSK1OC+xes3p37evJFvQWDeRVpgm2Wan\n" +
					"EYy8+KJ+2XZ7ElGsoRFrblaU9M4/sveLJykV6MYn7aNZ/kPMfv2GCKhyJOlQb3BC\n" +
					"MIHYDC9ai6CfklF5u/JM1D7ZFcZ5pXoI6BFQwsz0IFn9f1GWWyAuiGFKZTErFLmY\n" +
					"Bi5yOKamlSa9M/4TYcbzNynZ0sBygfNwoolXXTdGaJrc2Eidy9IL0sB48dUg+7yD\n" +
					"IUDTE+2AHPfjU9f6ffQipljsyFzd/AtxzGaytia3AEwA9gryIe6qQ2tCnYrCxQID\n" +
					"AQABo4ICUzCCAk8wHQYDVR0OBBYEFICx3djDDEVwD35653wqDutasNLFMB8GA1Ud\n" +
					"IwQYMBaAFM/tytAMqpoZP0yz1qwtixYS5tGUMIHKBgNVHR8EgcIwgb8wgbyggbmg\n" +
					"gbaGgbNsZGFwOi8vL0NOPU5JREFTdWJDQSxDTj1OQ0tNUzAyLENOPUNEUCxDTj1Q\n" +
					"dWJsaWMlMjBLZXklMjBTZXJ2aWNlcyxDTj1TZXJ2aWNlcyxDTj1Db25maWd1cmF0\n" +
					"aW9uLERDPW5pZHJuLERDPWdvLERDPXR6P2NlcnRpZmljYXRlUmV2b2NhdGlvbkxp\n" +
					"c3Q/YmFzZT9vYmplY3RDbGFzcz1jUkxEaXN0cmlidXRpb25Qb2ludDCBvgYIKwYB\n" +
					"BQUHAQEEgbEwga4wgasGCCsGAQUFBzAChoGebGRhcDovLy9DTj1OSURBU3ViQ0Es\n" +
					"Q049QUlBLENOPVB1YmxpYyUyMEtleSUyMFNlcnZpY2VzLENOPVNlcnZpY2VzLENO\n" +
					"PUNvbmZpZ3VyYXRpb24sREM9bmlkcm4sREM9Z28sREM9dHo/Y0FDZXJ0aWZpY2F0\n" +
					"ZT9iYXNlP29iamVjdENsYXNzPWNlcnRpZmljYXRpb25BdXRob3JpdHkwDgYDVR0P\n" +
					"AQH/BAQDAgWgMD0GCSsGAQQBgjcVBwQwMC4GJisGAQQBgjcVCIe5vneE4tIshfmX\n" +
					"P4f8qy29qG+BPIa9k3aCncB6AgFkAgEFMBMGA1UdJQQMMAoGCCsGAQUFBwMCMBsG\n" +
					"CSsGAQQBgjcVCgQOMAwwCgYIKwYBBQUHAwIwDQYJKoZIhvcNAQEFBQADggEBAIi2\n" +
					"Dax1fmgIYNbi+JZ1y8kviU/PwANGKxsbT6mFFWcjNCkj+RTC1fqZN7gczeeATIsa\n" +
					"2R+PLTpUST1KlgV6wBn4zgeDV6dRz6LY2oQv5aECm6sGnDfpbcXS/AmUkb6R4p0Y\n" +
					"dkEoe6AeOTur9OdrLU5ljJ8GWu+t/VVbcHOdzOpPQDP0wPgdJ40HmX/dNbltWphJ\n" +
					"AZRtO9MY1P6vQNi6e386jt/0IyW9Q3mcZ+J7gA8t7KUsdsNCrqKaKeFrk4uiTEqC\n" +
					"JGNviBfvj6ivi1pX3N9f+WVidtDld9kHNWN4woPuFv5NyJDhuUNf4Qrr0q8HwRi4\n" +
					"oVg+ohelA7WcK4JNwnI=\n" +
					"-----END CERTIFICATE-----\n";
			InputStream prvKeyInpStream = new ByteArrayInputStream(initialString.getBytes());
			AsymmetricKeyParameter publicKey = Utils.loadPublicKey(prvKeyInpStream);
			prvKeyInpStream.close();
			AsymmetricBlockCipher ei = new RSAEngine();
			AsymmetricBlockCipher e = new PKCS1Encoding(ei);
			e.init(true, publicKey);
			hexEncodedCipher = e.processBlock(data, 0, data.length);
		} catch (InvalidCipherTextException | IOException var7) {
			System.out.println(var7);
			var7.printStackTrace();
		}

		return hexEncodedCipher;
	}

	public byte[] signDigitally(byte[] messageBytes) {
		try {
			String initialString = "-----BEGIN RSA PRIVATE KEY-----\nMIIEowIBAAKCAQEAvB7FPcxwD1cNztQAj1BEiW+SVEqDMnlsglQRyCfqtPPFm8PD\nuKoGv3VAEkjd1hpkr7acrF8IgBH3HhH8S/qt00sBn160FkT5ps8aywKXwGl3oK/f\nJ1dLC1U/FmidZBwLxIuvO9+W3hKOFH6Qe6tb7GsrSg3FvrxRa2s5/lrJ/XAae6Bh\ne3s2jVq3hMcE1iQg0EQFGOYlQq482VKj+nel0UB5ZkfcLDatmavVae23RrTut1ul\noCrHlf1Y2m42UJKsEfJFDvrJdNr5bs/8fxN7iWN9HgQSbcIjntW1lFo3sPuEu1mP\n6k7mrrTxyPC3Rq0p6yP8JWLTcCwX6NbG6hgWkwIDAQABAoIBACepMRpzVJjXWLDn\nX2rXmGuFW0tsHjbxBKCBivIxADnAP0Q0xLnVFvEcjjKMrEBsdhfZnzR0IL2ni/sJ\n/bAjHKy0XpY0CDG9vwidF1//nvDRC4iUK68Gp2qvQUq/KZemlhCNBpfP7uXI8a1+\ndYWtPgcdHRZSywMIfv3vsF1g1L8am5PS5jAVdmhIfPFDMx06S0GT1vK4T9jbi5yx\n9QGVaDlE8INsIz59fhfl1VYP6naFGYYCULLS3U41jZNXMP8whESVYfOHYkQI4lqU\ngBS4WIYAGYZV/VCu5pj8tZfLnS1TSR+knqUamRwYSPKf2CdGKwxT6sDOkwCBinf6\nS5wFRKkCgYEA7gXH7ftxL7aLaPSMTEFSznwXb+eZONYuVi4+GNSaqTgqRG6VnQu6\n3ZuulBxxgwADe7XlgOIX89Vp+oVsEvuXnZ+JTQK0eN7KBJesP2nUWwcXxpT/Dvpe\ndO3QynhiXaXKPwsbStX/lEm0e+VAh7WX/MZRbI09ITeuCyCGaFdZwl8CgYEAylQd\nq9NF4ZpMsxT164GwdnErJXo9cTXMSGrCZXh9cSA+qEdMcSP9g/cGLmM7dT7TsCCS\npRUpq28l7Zw74htP9hlyDmTsqX5r/sy/hwh0Vai6xy8J388isyil7wJglyi8Y9Du\nZAarN142mSZRgymUdQEesMhsxdJbRgYqGTLrYE0CgYEAnLkRu5EkJdZ8VM8w0cTx\nUSREClrkeJgOSpCIMrFKZMGmkwh9Wrquf9xSRxOzah5ILNzIEOTOAK806M+RKa6f\nVhoFvb9aNXv8aKm1sMtuF8HD1e2lQ6d3KmasY6SAoEjZskkN32iosGsXe7ynkLPx\nL28ljeQxq/2Ni6YO2gUqBOECgYBiPgHCQu31o4SGmDDoz6oteLnokrhu1h6BM0V9\nG8pdObjy4NvfPAiHIVUhBRID1iPXq5lJC0OWeHvEKk5xda2X47ccAilgC4DI0gZV\nvOcwCKPYv6BdyighMiWQLmlUPHVtSi/W5d4RHnAIYTDHLVNmlMqgMFGQpJmpIKkk\nXzMnKQKBgBifqdcgxtlI8hzdoZGfVWYuoDN9hrVTz7EKmISCtlXHY0y5LlFIDPsv\n5B+d8XT5k/Z6A07o7amimZuTDvha5jLU1AmUZ9NhqTkmmdTRzghR5vg5Zletjcdd\n6oWWqwOYCk3zhhSiGuKLeJIX7syN/scyUq1drDNMHEsyU0OeWJ8D\n-----END RSA PRIVATE KEY-----\n";
			InputStream prvKeyInpStream = new ByteArrayInputStream(initialString.getBytes());
			AsymmetricKeyParameter privKey = Utils.loadPrivateKey(prvKeyInpStream);
			RSADigestSigner signer = new RSADigestSigner(new SHA1Digest());
			signer.init(true, privKey);
			signer.update(messageBytes, 0, messageBytes.length);
			prvKeyInpStream.close();
			return signer.generateSignature();
		} catch (CryptoException | DataLengthException | IOException var6) {
			throw new RuntimeException("Cannot generate RSA signature. " + var6.getMessage(), var6);
		}
	}

	private boolean verifySignature(byte[] messageBytes, byte[] signature) {
		try {
			String initialString = "-----BEGIN CERTIFICATE-----\n" +
					"MIIF2jCCBMKgAwIBAgITXwAAAfB7mshObgOoZgAAAAAB8DANBgkqhkiG9w0BAQUF\n" +
					"ADBTMRIwEAYKCZImiZPyLGQBGRYCdHoxEjAQBgoJkiaJk/IsZAEZFgJnbzEVMBMG\n" +
					"CgmSJomT8ixkARkWBW5pZHJuMRIwEAYDVQQDEwlOSURBU3ViQ0EwHhcNMjQxMDAz\n" +
					"MDkxNTIyWhcNMjYxMDAzMDkxNTIyWjCBpDELMAkGA1UEBhMCVFoxDDAKBgNVBAgT\n" +
					"A0RTTTEMMAoGA1UEBxMDRFNNMTAwLgYDVQQKEydOQVRJT05BTCBJREVOVElGSUNB\n" +
					"VElPTiBBVVRIT1JJVFkoTklEQSkxDTALBgNVBAsTBE5JREExGDAWBgNVBAMTD05J\n" +
					"REFDSUdTZWN1cml0eTEeMBwGCSqGSIb3DQEJARYPaW5mb0BuaWRhLmdvLnR6MIIB\n" +
					"IjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmkFvbONSZ//kTxL5CGtV01oI\n" +
					"5PHn8DAq5zQN5U8GTL9VjH6jGA7OXIhvZSK1OC+xes3p37evJFvQWDeRVpgm2Wan\n" +
					"EYy8+KJ+2XZ7ElGsoRFrblaU9M4/sveLJykV6MYn7aNZ/kPMfv2GCKhyJOlQb3BC\n" +
					"MIHYDC9ai6CfklF5u/JM1D7ZFcZ5pXoI6BFQwsz0IFn9f1GWWyAuiGFKZTErFLmY\n" +
					"Bi5yOKamlSa9M/4TYcbzNynZ0sBygfNwoolXXTdGaJrc2Eidy9IL0sB48dUg+7yD\n" +
					"IUDTE+2AHPfjU9f6ffQipljsyFzd/AtxzGaytia3AEwA9gryIe6qQ2tCnYrCxQID\n" +
					"AQABo4ICUzCCAk8wHQYDVR0OBBYEFICx3djDDEVwD35653wqDutasNLFMB8GA1Ud\n" +
					"IwQYMBaAFM/tytAMqpoZP0yz1qwtixYS5tGUMIHKBgNVHR8EgcIwgb8wgbyggbmg\n" +
					"gbaGgbNsZGFwOi8vL0NOPU5JREFTdWJDQSxDTj1OQ0tNUzAyLENOPUNEUCxDTj1Q\n" +
					"dWJsaWMlMjBLZXklMjBTZXJ2aWNlcyxDTj1TZXJ2aWNlcyxDTj1Db25maWd1cmF0\n" +
					"aW9uLERDPW5pZHJuLERDPWdvLERDPXR6P2NlcnRpZmljYXRlUmV2b2NhdGlvbkxp\n" +
					"c3Q/YmFzZT9vYmplY3RDbGFzcz1jUkxEaXN0cmlidXRpb25Qb2ludDCBvgYIKwYB\n" +
					"BQUHAQEEgbEwga4wgasGCCsGAQUFBzAChoGebGRhcDovLy9DTj1OSURBU3ViQ0Es\n" +
					"Q049QUlBLENOPVB1YmxpYyUyMEtleSUyMFNlcnZpY2VzLENOPVNlcnZpY2VzLENO\n" +
					"PUNvbmZpZ3VyYXRpb24sREM9bmlkcm4sREM9Z28sREM9dHo/Y0FDZXJ0aWZpY2F0\n" +
					"ZT9iYXNlP29iamVjdENsYXNzPWNlcnRpZmljYXRpb25BdXRob3JpdHkwDgYDVR0P\n" +
					"AQH/BAQDAgWgMD0GCSsGAQQBgjcVBwQwMC4GJisGAQQBgjcVCIe5vneE4tIshfmX\n" +
					"P4f8qy29qG+BPIa9k3aCncB6AgFkAgEFMBMGA1UdJQQMMAoGCCsGAQUFBwMCMBsG\n" +
					"CSsGAQQBgjcVCgQOMAwwCgYIKwYBBQUHAwIwDQYJKoZIhvcNAQEFBQADggEBAIi2\n" +
					"Dax1fmgIYNbi+JZ1y8kviU/PwANGKxsbT6mFFWcjNCkj+RTC1fqZN7gczeeATIsa\n" +
					"2R+PLTpUST1KlgV6wBn4zgeDV6dRz6LY2oQv5aECm6sGnDfpbcXS/AmUkb6R4p0Y\n" +
					"dkEoe6AeOTur9OdrLU5ljJ8GWu+t/VVbcHOdzOpPQDP0wPgdJ40HmX/dNbltWphJ\n" +
					"AZRtO9MY1P6vQNi6e386jt/0IyW9Q3mcZ+J7gA8t7KUsdsNCrqKaKeFrk4uiTEqC\n" +
					"JGNviBfvj6ivi1pX3N9f+WVidtDld9kHNWN4woPuFv5NyJDhuUNf4Qrr0q8HwRi4\n" +
					"oVg+ohelA7WcK4JNwnI=\n" +
					"-----END CERTIFICATE-----\n";
			InputStream pubKeyInpStream = new ByteArrayInputStream(initialString.getBytes());
			AsymmetricKeyParameter publKey = NIDAVerificationService.KeyUtil.loadPublicKey(pubKeyInpStream);
			RSADigestSigner signer = new RSADigestSigner(new SHA1Digest());
			signer.init(false, publKey);
			signer.update(messageBytes, 0, messageBytes.length);
			boolean isValidSignature = signer.verifySignature(signature);
			pubKeyInpStream.close();
			return isValidSignature;
		} catch (Exception var8) {
			var8.printStackTrace();
			throw new RuntimeException(var8.getMessage());
		}
	}

	private byte[] encryptRijndael256(String data, byte[] key, byte[] iv) {
		try {
			logger.error("encryiping ==================");
			logger.error(data);
			PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new RijndaelEngine(256)), new KeyUtil.MyPadder());
			CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
			cipher.init(true, ivAndKey);
			byte[] input = data.getBytes();
			byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
			int cipherLength = cipher.processBytes(input, 0, input.length, cipherText, 0);
			cipher.doFinal(cipherText, cipherLength);
			return cipherText;
		} catch (Exception var9) {
			throw new RuntimeException(var9);
		}
	}

	private byte[] decryptRijndael256(byte[] encData, byte[] key, byte[] iv) {
		try {
			PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new RijndaelEngine(256)), new KeyUtil.MyPadder());
			CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
			cipher.init(false, ivAndKey);
			byte[] plainText = new byte[cipher.getOutputSize(encData.length)];
			int cipherLength = cipher.processBytes(encData, 0, encData.length, plainText, 0);
			cipher.doFinal(plainText, cipherLength);
			return plainText;
		} catch (Exception var8) {
			throw new RuntimeException(var8);
		}
	}

	private byte[] generateRijdaelKey(String key) {
		Object var2 = null;

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] sha256Bytes = digest.digest(key.getBytes(StandardCharsets.UTF_8));
			int keySize = 32;
			byte[] keyBytes = Arrays.copyOf(sha256Bytes, keySize);
			return keyBytes;
		} catch (Exception var6) {
			var6.printStackTrace();
			throw new RuntimeException(var6.getMessage());
		}
	}

	public static String generateRandomString(int length) {
		String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom RANDOM = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);

		for(int i = 0; i < length; ++i) {
			int randomIndex = RANDOM.nextInt(ALPHABET.length());
			sb.append(ALPHABET.charAt(randomIndex));
		}

		return sb.toString();
	}

	public static class KeyUtil {
		public static AsymmetricKeyParameter loadPublicKey(InputStream is) {
			X509CertificateHolder spki = (X509CertificateHolder)readPemObject(is);

			try {
				return PublicKeyFactory.createKey(spki.getSubjectPublicKeyInfo());
			} catch (IOException var3) {
				throw new RuntimeException("Cannot create public key object based on input data", var3);
			}
		}

		public static AsymmetricKeyParameter loadPrivateKey(InputStream is) {
			PEMKeyPair keyPair = (PEMKeyPair)readPemObject(is);
			PrivateKeyInfo pki = keyPair.getPrivateKeyInfo();

			try {
				return PrivateKeyFactory.createKey(pki);
			} catch (IOException var4) {
				throw new RuntimeException("Cannot create private key object based on input data", var4);
			}
		}

		private static Object readPemObject(InputStream is) {
			try {
				Validate.notNull(is, "Input data stream cannot be null", new Object[0]);
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				PEMParser pemParser = new PEMParser(isr);
				Object obj = pemParser.readObject();
				if (obj == null) {
					throw new Exception("No PEM object found");
				} else {
					return obj;
				}
			} catch (Throwable var4) {
				throw new RuntimeException("Cannot read PEM object from input data", var4);
			}
		}


	public static class MyPadder extends ZeroBytePadding {
		public int addPadding(byte[] in, int inOff) {
			int added;
			for(added = in.length - inOff; inOff < in.length; ++inOff) {
				in[inOff] = (byte)added;
			}

			return added;
		}

		public int padCount(byte[] in) throws InvalidCipherTextException {
			int count = in.length;
			byte lastByte = 0;
			if (count > 0 && lastByte <= 20 && lastByte > 0) {

				for(lastByte = in[count - 1]; count > 0 && in[count - 1] == lastByte; --count) {
				}

				int processed = in.length - count;
				return lastByte == processed ? processed : 0;
			} else {
				return 0;
			}
		}
	}
}
}
