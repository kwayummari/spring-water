package tz.co.itrust.services.nida.soap.datacontract;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _RequestCryptoInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "RequestCryptoInfo");
    private final static QName _RequestBody_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "RequestBody");
    private final static QName _ResponseCryptoInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "ResponseCryptoInfo");
    private final static QName _CIGResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "CIGResponse");
    private final static QName _CIGRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "CIGRequest");
    private final static QName _ResponseBody_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "ResponseBody");
    private final static QName _ResponseHeader_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "ResponseHeader");
    private final static QName _RequestHeader_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "RequestHeader");
    
    // Element names
    private final static QName _ResponseBodyPayload_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "Payload");
    private final static QName _ResponseBodySignature_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "Signature");
    private final static QName _ResponseBodyCryptoInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "CryptoInfo");
    private final static QName _ResponseCryptoInfoEncryptedCryptoKey_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "EncryptedCryptoKey");
    private final static QName _ResponseCryptoInfoEncryptedCryptoIV_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "EncryptedCryptoIV");
    private final static QName _CIGRequestBody_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "Body");
    private final static QName _CIGRequestHeader_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "Header");
    private final static QName _RequestHeaderTimeStamp_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "TimeStamp");
    private final static QName _RequestHeaderId_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "Id");
    private final static QName _RequestHeaderClientNameOrIP_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "ClientNameOrIP");
    private final static QName _RequestHeaderUserId_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "UserId");
    private final static QName _CIGResponseCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "Code");

    public ObjectFactory() {
    }

    // Create instances
    public ResponseHeader createResponseHeader() {
        return new ResponseHeader();
    }

    public CIGRequest createCIGRequest() {
        return new CIGRequest();
    }

    public RequestCryptoInfo createRequestCryptoInfo() {
        return new RequestCryptoInfo();
    }

    public RequestHeader createRequestHeader() {
        return new RequestHeader();
    }

    public CIGResponse createCIGResponse() {
        return new CIGResponse();
    }

    public ResponseCryptoInfo createResponseCryptoInfo() {
        return new ResponseCryptoInfo();
    }

    public RequestBody createRequestBody() {
        return new RequestBody();
    }

    public ResponseBody createResponseBody() {
        return new ResponseBody();
    }

    // JAXBElement creators for CIGRequest
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Body", scope = CIGRequest.class)
    public JAXBElement<RequestBody> createCIGRequestBody(RequestBody value) {
        return new JAXBElement<RequestBody>(_CIGRequestBody_QNAME, RequestBody.class, CIGRequest.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Header", scope = CIGRequest.class)
    public JAXBElement<RequestHeader> createCIGRequestHeader(RequestHeader value) {
        return new JAXBElement<RequestHeader>(_CIGRequestHeader_QNAME, RequestHeader.class, CIGRequest.class, value);
    }

    // JAXBElement creators for RequestHeader
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "TimeStamp", scope = RequestHeader.class)
    public JAXBElement<String> createRequestHeaderTimeStamp(String value) {
        return new JAXBElement<String>(_RequestHeaderTimeStamp_QNAME, String.class, RequestHeader.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Id", scope = RequestHeader.class)
    public JAXBElement<String> createRequestHeaderId(String value) {
        return new JAXBElement<String>(_RequestHeaderId_QNAME, String.class, RequestHeader.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "ClientNameOrIP", scope = RequestHeader.class)
    public JAXBElement<String> createRequestHeaderClientNameOrIP(String value) {
        return new JAXBElement<String>(_RequestHeaderClientNameOrIP_QNAME, String.class, RequestHeader.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "UserId", scope = RequestHeader.class)
    public JAXBElement<String> createRequestHeaderUserId(String value) {
        return new JAXBElement<String>(_RequestHeaderUserId_QNAME, String.class, RequestHeader.class, value);
    }

    // JAXBElement creators for RequestBody
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Payload", scope = RequestBody.class)
    public JAXBElement<String> createRequestBodyPayload(String value) {
        return new JAXBElement<String>(_ResponseBodyPayload_QNAME, String.class, RequestBody.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Signature", scope = RequestBody.class)
    public JAXBElement<String> createRequestBodySignature(String value) {
        return new JAXBElement<String>(_ResponseBodySignature_QNAME, String.class, RequestBody.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "CryptoInfo", scope = RequestBody.class)
    public JAXBElement<RequestCryptoInfo> createRequestBodyCryptoInfo(RequestCryptoInfo value) {
        return new JAXBElement<RequestCryptoInfo>(_ResponseBodyCryptoInfo_QNAME, RequestCryptoInfo.class, RequestBody.class, value);
    }

    // JAXBElement creators for RequestCryptoInfo
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "EncryptedCryptoKey", scope = RequestCryptoInfo.class)
    public JAXBElement<String> createRequestCryptoInfoEncryptedCryptoKey(String value) {
        return new JAXBElement<String>(_ResponseCryptoInfoEncryptedCryptoKey_QNAME, String.class, RequestCryptoInfo.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "EncryptedCryptoIV", scope = RequestCryptoInfo.class)
    public JAXBElement<String> createRequestCryptoInfoEncryptedCryptoIV(String value) {
        return new JAXBElement<String>(_ResponseCryptoInfoEncryptedCryptoIV_QNAME, String.class, RequestCryptoInfo.class, value);
    }

    // JAXBElement creators for CIGResponse
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Body", scope = CIGResponse.class)
    public JAXBElement<ResponseBody> createCIGResponseBody(ResponseBody value) {
        return new JAXBElement<ResponseBody>(_CIGRequestBody_QNAME, ResponseBody.class, CIGResponse.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Code", scope = CIGResponse.class)
    public JAXBElement<String> createCIGResponseCode(String value) {
        return new JAXBElement<String>(_CIGResponseCode_QNAME, String.class, CIGResponse.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Header", scope = CIGResponse.class)
    public JAXBElement<ResponseHeader> createCIGResponseHeader(ResponseHeader value) {
        return new JAXBElement<ResponseHeader>(_CIGRequestHeader_QNAME, ResponseHeader.class, CIGResponse.class, value);
    }

    // JAXBElement creators for ResponseBody
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Payload", scope = ResponseBody.class)
    public JAXBElement<String> createResponseBodyPayload(String value) {
        return new JAXBElement<String>(_ResponseBodyPayload_QNAME, String.class, ResponseBody.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Signature", scope = ResponseBody.class)
    public JAXBElement<String> createResponseBodySignature(String value) {
        return new JAXBElement<String>(_ResponseBodySignature_QNAME, String.class, ResponseBody.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "CryptoInfo", scope = ResponseBody.class)
    public JAXBElement<ResponseCryptoInfo> createResponseBodyCryptoInfo(ResponseCryptoInfo value) {
        return new JAXBElement<ResponseCryptoInfo>(_ResponseBodyCryptoInfo_QNAME, ResponseCryptoInfo.class, ResponseBody.class, value);
    }

    // JAXBElement creators for ResponseCryptoInfo
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "EncryptedCryptoKey", scope = ResponseCryptoInfo.class)
    public JAXBElement<String> createResponseCryptoInfoEncryptedCryptoKey(String value) {
        return new JAXBElement<String>(_ResponseCryptoInfoEncryptedCryptoKey_QNAME, String.class, ResponseCryptoInfo.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "EncryptedCryptoIV", scope = ResponseCryptoInfo.class)
    public JAXBElement<String> createResponseCryptoInfoEncryptedCryptoIV(String value) {
        return new JAXBElement<String>(_ResponseCryptoInfoEncryptedCryptoIV_QNAME, String.class, ResponseCryptoInfo.class, value);
    }

    // JAXBElement creators for ResponseHeader
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "TimeStamp", scope = ResponseHeader.class)
    public JAXBElement<String> createResponseHeaderTimeStamp(String value) {
        return new JAXBElement<String>(_RequestHeaderTimeStamp_QNAME, String.class, ResponseHeader.class, value);
    }

    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Id", scope = ResponseHeader.class)
    public JAXBElement<String> createResponseHeaderId(String value) {
        return new JAXBElement<String>(_RequestHeaderId_QNAME, String.class, ResponseHeader.class, value);
    }
}