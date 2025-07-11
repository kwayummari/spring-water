
package tz.co.itrust.services.nida.tempuri;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import tz.co.itrust.services.nida.datacontract.schemas.CIGRequest;
import tz.co.itrust.services.nida.datacontract.schemas.CIGResponse;

import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AltBiometricVerificationResponseAltBiometricVerificationResult_QNAME = new QName("http://tempuri.org/", "AltBiometricVerificationResult");
    private final static QName _IdentificationRequestResponseIdentificationRequestResult_QNAME = new QName("http://tempuri.org/", "IdentificationRequestResult");
    private final static QName _RQVerificationIRequest_QNAME = new QName("http://tempuri.org/", "iRequest");
    private final static QName _TransactionVerificationResponseTransactionVerificationResult_QNAME = new QName("http://tempuri.org/", "TransactionVerificationResult");
    private final static QName _OTPVerificationResponseOTPVerificationResult_QNAME = new QName("http://tempuri.org/", "OTPVerificationResult");
    private final static QName _BiometricVerificationResponseBiometricVerificationResult_QNAME = new QName("http://tempuri.org/", "BiometricVerificationResult");
    private final static QName _RQVerificationResponseRQVerificationResult_QNAME = new QName("http://tempuri.org/", "RQVerificationResult");
    private final static QName _QueryFullDemographicResponseQueryFullDemographicResult_QNAME = new QName("http://tempuri.org/", "QueryFullDemographicResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IdentificationRequestResponse }
     * 
     */
    public IdentificationRequestResponse createIdentificationRequestResponse() {
        return new IdentificationRequestResponse();
    }

    /**
     * Create an instance of {@link RQVerificationResponse }
     * 
     */
    public RQVerificationResponse createRQVerificationResponse() {
        return new RQVerificationResponse();
    }

    /**
     * Create an instance of {@link AltBiometricVerificationResponse }
     * 
     */
    public AltBiometricVerificationResponse createAltBiometricVerificationResponse() {
        return new AltBiometricVerificationResponse();
    }

    /**
     * Create an instance of {@link BiometricVerification }
     * 
     */
    public BiometricVerification createBiometricVerification() {
        return new BiometricVerification();
    }

    /**
     * Create an instance of {@link QueryFullDemographicResponse }
     * 
     */
    public QueryFullDemographicResponse createQueryFullDemographicResponse() {
        return new QueryFullDemographicResponse();
    }

    /**
     * Create an instance of {@link RQVerification }
     * 
     */
    public RQVerification createRQVerification() {
        return new RQVerification();
    }

    /**
     * Create an instance of {@link TransactionVerification }
     * 
     */
    public TransactionVerification createTransactionVerification() {
        return new TransactionVerification();
    }

    /**
     * Create an instance of {@link QueryFullDemographic }
     * 
     */
    public QueryFullDemographic createQueryFullDemographic() {
        return new QueryFullDemographic();
    }

    /**
     * Create an instance of {@link VerificationResponse }
     * 
     */
    public VerificationResponse createBiometricVerificationResponse() {
        return new VerificationResponse();
    }

    /**
     * Create an instance of {@link AltBiometricVerification }
     * 
     */
    public AltBiometricVerification createAltBiometricVerification() {
        return new AltBiometricVerification();
    }

    /**
     * Create an instance of {@link TransactionVerificationResponse }
     * 
     */
    public TransactionVerificationResponse createTransactionVerificationResponse() {
        return new TransactionVerificationResponse();
    }

    /**
     * Create an instance of {@link OTPVerification }
     * 
     */
    public OTPVerification createOTPVerification() {
        return new OTPVerification();
    }

    /**
     * Create an instance of {@link OTPVerificationResponse }
     * 
     */
    public OTPVerificationResponse createOTPVerificationResponse() {
        return new OTPVerificationResponse();
    }

    /**
     * Create an instance of {@link IdentificationRequest }
     * 
     */
    public IdentificationRequest createIdentificationRequest() {
        return new IdentificationRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "AltBiometricVerificationResult", scope = AltBiometricVerificationResponse.class)
    public JAXBElement<CIGResponse> createAltBiometricVerificationResponseAltBiometricVerificationResult(CIGResponse value) {
        return new JAXBElement<CIGResponse>(_AltBiometricVerificationResponseAltBiometricVerificationResult_QNAME, CIGResponse.class, AltBiometricVerificationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "IdentificationRequestResult", scope = IdentificationRequestResponse.class)
    public JAXBElement<CIGResponse> createIdentificationRequestResponseIdentificationRequestResult(CIGResponse value) {
        return new JAXBElement<CIGResponse>(_IdentificationRequestResponseIdentificationRequestResult_QNAME, CIGResponse.class, IdentificationRequestResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "iRequest", scope = RQVerification.class)
    public JAXBElement<CIGRequest> createRQVerificationIRequest(CIGRequest value) {
        return new JAXBElement<CIGRequest>(_RQVerificationIRequest_QNAME, CIGRequest.class, RQVerification.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "iRequest", scope = TransactionVerification.class)
    public JAXBElement<CIGRequest> createTransactionVerificationIRequest(CIGRequest value) {
        return new JAXBElement<CIGRequest>(_RQVerificationIRequest_QNAME, CIGRequest.class, TransactionVerification.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "TransactionVerificationResult", scope = TransactionVerificationResponse.class)
    public JAXBElement<CIGResponse> createTransactionVerificationResponseTransactionVerificationResult(CIGResponse value) {
        return new JAXBElement<CIGResponse>(_TransactionVerificationResponseTransactionVerificationResult_QNAME, CIGResponse.class, TransactionVerificationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "iRequest", scope = OTPVerification.class)
    public JAXBElement<CIGRequest> createOTPVerificationIRequest(CIGRequest value) {
        return new JAXBElement<CIGRequest>(_RQVerificationIRequest_QNAME, CIGRequest.class, OTPVerification.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "OTPVerificationResult", scope = OTPVerificationResponse.class)
    public JAXBElement<CIGResponse> createOTPVerificationResponseOTPVerificationResult(CIGResponse value) {
        return new JAXBElement<CIGResponse>(_OTPVerificationResponseOTPVerificationResult_QNAME, CIGResponse.class, OTPVerificationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "iRequest", scope = IdentificationRequest.class)
    public JAXBElement<CIGRequest> createIdentificationRequestIRequest(CIGRequest value) {
        return new JAXBElement<CIGRequest>(_RQVerificationIRequest_QNAME, CIGRequest.class, IdentificationRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "BiometricVerificationResult", scope = VerificationResponse.class)
    public JAXBElement<CIGResponse> createBiometricVerificationResponseBiometricVerificationResult(CIGResponse value) {
        return new JAXBElement<CIGResponse>(_BiometricVerificationResponseBiometricVerificationResult_QNAME, CIGResponse.class, VerificationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "iRequest", scope = BiometricVerification.class)
    public JAXBElement<CIGRequest> createBiometricVerificationIRequest(CIGRequest value) {
        return new JAXBElement<CIGRequest>(_RQVerificationIRequest_QNAME, CIGRequest.class, BiometricVerification.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "iRequest", scope = QueryFullDemographic.class)
    public JAXBElement<CIGRequest> createQueryFullDemographicIRequest(CIGRequest value) {
        return new JAXBElement<CIGRequest>(_RQVerificationIRequest_QNAME, CIGRequest.class, QueryFullDemographic.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "RQVerificationResult", scope = RQVerificationResponse.class)
    public JAXBElement<CIGResponse> createRQVerificationResponseRQVerificationResult(CIGResponse value) {
        return new JAXBElement<CIGResponse>(_RQVerificationResponseRQVerificationResult_QNAME, CIGResponse.class, RQVerificationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "QueryFullDemographicResult", scope = QueryFullDemographicResponse.class)
    public JAXBElement<CIGResponse> createQueryFullDemographicResponseQueryFullDemographicResult(CIGResponse value) {
        return new JAXBElement<CIGResponse>(_QueryFullDemographicResponseQueryFullDemographicResult_QNAME, CIGResponse.class, QueryFullDemographicResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "iRequest", scope = AltBiometricVerification.class)
    public JAXBElement<CIGRequest> createAltBiometricVerificationIRequest(CIGRequest value) {
        return new JAXBElement<CIGRequest>(_RQVerificationIRequest_QNAME, CIGRequest.class, AltBiometricVerification.class, value);
    }

}
