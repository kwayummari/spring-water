
package tz.co.itrust.services.nida.datacontract.schemas;


import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.nid_api package. 
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

    private final static QName _RequestCryptoInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "RequestCryptoInfo");
    private final static QName _RequestBody_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "RequestBody");
    private final static QName _ResponseCryptoInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "ResponseCryptoInfo");
    private final static QName _CIGResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "CIGResponse");
    private final static QName _CIGRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "CIGRequest");
    private final static QName _ResponseBody_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "ResponseBody");
    private final static QName _ResponseHeader_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "ResponseHeader");
    private final static QName _RequestHeader_QNAME = new QName("http://schemas.datacontract.org/2004/07/NID_API", "RequestHeader");
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

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.nid_api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResponseHeader }
     * 
     */
    public ResponseHeader createResponseHeader() {
        return new ResponseHeader();
    }

    /**
     * Create an instance of {@link CIGRequest }
     * 
     */
    public CIGRequest createCIGRequest() {
        return new CIGRequest();
    }

    /**
     * Create an instance of {@link RequestCryptoInfo }
     * 
     */
    public RequestCryptoInfo createRequestCryptoInfo() {
        return new RequestCryptoInfo();
    }

    /**
     * Create an instance of {@link RequestHeader }
     * 
     */
    public RequestHeader createRequestHeader() {
        return new RequestHeader();
    }

    /**
     * Create an instance of {@link CIGResponse }
     * 
     */
    public CIGResponse createCIGResponse() {
        return new CIGResponse();
    }

    /**
     * Create an instance of {@link ResponseCryptoInfo }
     * 
     */
    public ResponseCryptoInfo createResponseCryptoInfo() {
        return new ResponseCryptoInfo();
    }

    /**
     * Create an instance of {@link RequestBody }
     * 
     */
    public RequestBody createRequestBody() {
        return new RequestBody();
    }

    /**
     * Create an instance of {@link ResponseBody }
     * 
     */
    public ResponseBody createResponseBody() {
        return new ResponseBody();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestCryptoInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "RequestCryptoInfo")
    public JAXBElement<RequestCryptoInfo> createRequestCryptoInfo(RequestCryptoInfo value) {
        return new JAXBElement<RequestCryptoInfo>(_RequestCryptoInfo_QNAME, RequestCryptoInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestBody }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "RequestBody")
    public JAXBElement<RequestBody> createRequestBody(RequestBody value) {
        return new JAXBElement<RequestBody>(_RequestBody_QNAME, RequestBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseCryptoInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "ResponseCryptoInfo")
    public JAXBElement<ResponseCryptoInfo> createResponseCryptoInfo(ResponseCryptoInfo value) {
        return new JAXBElement<ResponseCryptoInfo>(_ResponseCryptoInfo_QNAME, ResponseCryptoInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "CIGResponse")
    public JAXBElement<CIGResponse> createCIGResponse(CIGResponse value) {
        return new JAXBElement<CIGResponse>(_CIGResponse_QNAME, CIGResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CIGRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "CIGRequest")
    public JAXBElement<CIGRequest> createCIGRequest(CIGRequest value) {
        return new JAXBElement<CIGRequest>(_CIGRequest_QNAME, CIGRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseBody }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "ResponseBody")
    public JAXBElement<ResponseBody> createResponseBody(ResponseBody value) {
        return new JAXBElement<ResponseBody>(_ResponseBody_QNAME, ResponseBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "ResponseHeader")
    public JAXBElement<ResponseHeader> createResponseHeader(ResponseHeader value) {
        return new JAXBElement<ResponseHeader>(_ResponseHeader_QNAME, ResponseHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "RequestHeader")
    public JAXBElement<RequestHeader> createRequestHeader(RequestHeader value) {
        return new JAXBElement<RequestHeader>(_RequestHeader_QNAME, RequestHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Payload", scope = ResponseBody.class)
    public JAXBElement<String> createResponseBodyPayload(String value) {
        return new JAXBElement<String>(_ResponseBodyPayload_QNAME, String.class, ResponseBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Signature", scope = ResponseBody.class)
    public JAXBElement<String> createResponseBodySignature(String value) {
        return new JAXBElement<String>(_ResponseBodySignature_QNAME, String.class, ResponseBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseCryptoInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "CryptoInfo", scope = ResponseBody.class)
    public JAXBElement<ResponseCryptoInfo> createResponseBodyCryptoInfo(ResponseCryptoInfo value) {
        return new JAXBElement<ResponseCryptoInfo>(_ResponseBodyCryptoInfo_QNAME, ResponseCryptoInfo.class, ResponseBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "EncryptedCryptoKey", scope = ResponseCryptoInfo.class)
    public JAXBElement<String> createResponseCryptoInfoEncryptedCryptoKey(String value) {
        return new JAXBElement<String>(_ResponseCryptoInfoEncryptedCryptoKey_QNAME, String.class, ResponseCryptoInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "EncryptedCryptoIV", scope = ResponseCryptoInfo.class)
    public JAXBElement<String> createResponseCryptoInfoEncryptedCryptoIV(String value) {
        return new JAXBElement<String>(_ResponseCryptoInfoEncryptedCryptoIV_QNAME, String.class, ResponseCryptoInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestBody }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Body", scope = CIGRequest.class)
    public JAXBElement<RequestBody> createCIGRequestBody(RequestBody value) {
        return new JAXBElement<RequestBody>(_CIGRequestBody_QNAME, RequestBody.class, CIGRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Header", scope = CIGRequest.class)
    public JAXBElement<RequestHeader> createCIGRequestHeader(RequestHeader value) {
        return new JAXBElement<RequestHeader>(_CIGRequestHeader_QNAME, RequestHeader.class, CIGRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "TimeStamp", scope = RequestHeader.class)
    public JAXBElement<String> createRequestHeaderTimeStamp(String value) {
        return new JAXBElement<String>(_RequestHeaderTimeStamp_QNAME, String.class, RequestHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Id", scope = RequestHeader.class)
    public JAXBElement<String> createRequestHeaderId(String value) {
        return new JAXBElement<String>(_RequestHeaderId_QNAME, String.class, RequestHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "ClientNameOrIP", scope = RequestHeader.class)
    public JAXBElement<String> createRequestHeaderClientNameOrIP(String value) {
        return new JAXBElement<String>(_RequestHeaderClientNameOrIP_QNAME, String.class, RequestHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "UserId", scope = RequestHeader.class)
    public JAXBElement<String> createRequestHeaderUserId(String value) {
        return new JAXBElement<String>(_RequestHeaderUserId_QNAME, String.class, RequestHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseBody }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Body", scope = CIGResponse.class)
    public JAXBElement<ResponseBody> createCIGResponseBody(ResponseBody value) {
        return new JAXBElement<ResponseBody>(_CIGRequestBody_QNAME, ResponseBody.class, CIGResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Code", scope = CIGResponse.class)
    public JAXBElement<String> createCIGResponseCode(String value) {
        return new JAXBElement<String>(_CIGResponseCode_QNAME, String.class, CIGResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Header", scope = CIGResponse.class)
    public JAXBElement<ResponseHeader> createCIGResponseHeader(ResponseHeader value) {
        return new JAXBElement<ResponseHeader>(_CIGRequestHeader_QNAME, ResponseHeader.class, CIGResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Payload", scope = RequestBody.class)
    public JAXBElement<String> createRequestBodyPayload(String value) {
        return new JAXBElement<String>(_ResponseBodyPayload_QNAME, String.class, RequestBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Signature", scope = RequestBody.class)
    public JAXBElement<String> createRequestBodySignature(String value) {
        return new JAXBElement<String>(_ResponseBodySignature_QNAME, String.class, RequestBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestCryptoInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "CryptoInfo", scope = RequestBody.class)
    public JAXBElement<RequestCryptoInfo> createRequestBodyCryptoInfo(RequestCryptoInfo value) {
        return new JAXBElement<RequestCryptoInfo>(_ResponseBodyCryptoInfo_QNAME, RequestCryptoInfo.class, RequestBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "TimeStamp", scope = ResponseHeader.class)
    public JAXBElement<String> createResponseHeaderTimeStamp(String value) {
        return new JAXBElement<String>(_RequestHeaderTimeStamp_QNAME, String.class, ResponseHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "Id", scope = ResponseHeader.class)
    public JAXBElement<String> createResponseHeaderId(String value) {
        return new JAXBElement<String>(_RequestHeaderId_QNAME, String.class, ResponseHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "EncryptedCryptoKey", scope = RequestCryptoInfo.class)
    public JAXBElement<String> createRequestCryptoInfoEncryptedCryptoKey(String value) {
        return new JAXBElement<String>(_ResponseCryptoInfoEncryptedCryptoKey_QNAME, String.class, RequestCryptoInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/NID_API", name = "EncryptedCryptoIV", scope = RequestCryptoInfo.class)
    public JAXBElement<String> createRequestCryptoInfoEncryptedCryptoIV(String value) {
        return new JAXBElement<String>(_ResponseCryptoInfoEncryptedCryptoIV_QNAME, String.class, RequestCryptoInfo.class, value);
    }

}
