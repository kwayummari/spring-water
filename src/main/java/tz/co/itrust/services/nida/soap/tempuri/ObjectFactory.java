package tz.co.itrust.services.nida.soap.tempuri;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import tz.co.itrust.services.nida.soap.datacontract.CIGRequest;
import tz.co.itrust.services.nida.soap.datacontract.CIGResponse;

import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _RQVerificationIRequest_QNAME = new QName("http://tempuri.org/", "iRequest");
    private final static QName _RQVerificationResponseRQVerificationResult_QNAME = new QName("http://tempuri.org/", "RQVerificationResult");
    private final static QName _BiometricVerificationResponseBiometricVerificationResult_QNAME = new QName("http://tempuri.org/", "BiometricVerificationResult");

    public ObjectFactory() {
    }

    public RQVerification createRQVerification() {
        return new RQVerification();
    }

    public RQVerificationResponse createRQVerificationResponse() {
        return new RQVerificationResponse();
    }

    public BiometricVerification createBiometricVerification() {
        return new BiometricVerification();
    }

    public VerificationResponse createBiometricVerificationResponse() {
        return new VerificationResponse();
    }

    @XmlElementDecl(namespace = "http://tempuri.org/", name = "iRequest", scope = RQVerification.class)
    public JAXBElement<CIGRequest> createRQVerificationIRequest(CIGRequest value) {
        return new JAXBElement<CIGRequest>(_RQVerificationIRequest_QNAME, CIGRequest.class, RQVerification.class, value);
    }

    @XmlElementDecl(namespace = "http://tempuri.org/", name = "RQVerificationResult", scope = RQVerificationResponse.class)
    public JAXBElement<CIGResponse> createRQVerificationResponseRQVerificationResult(CIGResponse value) {
        return new JAXBElement<CIGResponse>(_RQVerificationResponseRQVerificationResult_QNAME, CIGResponse.class, RQVerificationResponse.class, value);
    }

    @XmlElementDecl(namespace = "http://tempuri.org/", name = "iRequest", scope = BiometricVerification.class)
    public JAXBElement<CIGRequest> createBiometricVerificationIRequest(CIGRequest value) {
        return new JAXBElement<CIGRequest>(_RQVerificationIRequest_QNAME, CIGRequest.class, BiometricVerification.class, value);
    }

    @XmlElementDecl(namespace = "http://tempuri.org/", name = "BiometricVerificationResult", scope = VerificationResponse.class)
    public JAXBElement<CIGResponse> createBiometricVerificationResponseBiometricVerificationResult(CIGResponse value) {
        return new JAXBElement<CIGResponse>(_BiometricVerificationResponseBiometricVerificationResult_QNAME, CIGResponse.class, VerificationResponse.class, value);
    }
}