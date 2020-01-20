
package com.company.project.module.eam.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.company.project.module.eam.client package. 
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

    private final static QName _GetDataInfoResponse_QNAME = new QName("http://www.joven.com.cn/ws/GetDataInterfaceService", "getDataInfoResponse");
    private final static QName _GetDataInfo_QNAME = new QName("http://www.joven.com.cn/ws/GetDataInterfaceService", "getDataInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.company.project.module.eam.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDataInfo }
     * 
     */
    public GetDataInfo createGetDataInfo() {
        return new GetDataInfo();
    }

    /**
     * Create an instance of {@link GetDataInfoResponse }
     * 
     */
    public GetDataInfoResponse createGetDataInfoResponse() {
        return new GetDataInfoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.joven.com.cn/ws/GetDataInterfaceService", name = "getDataInfoResponse")
    public JAXBElement<GetDataInfoResponse> createGetDataInfoResponse(GetDataInfoResponse value) {
        return new JAXBElement<GetDataInfoResponse>(_GetDataInfoResponse_QNAME, GetDataInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.joven.com.cn/ws/GetDataInterfaceService", name = "getDataInfo")
    public JAXBElement<GetDataInfo> createGetDataInfo(GetDataInfo value) {
        return new JAXBElement<GetDataInfo>(_GetDataInfo_QNAME, GetDataInfo.class, null, value);
    }

}
