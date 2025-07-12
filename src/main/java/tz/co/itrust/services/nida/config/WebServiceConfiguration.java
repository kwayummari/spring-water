package tz.co.itrust.services.nida.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPException;
import java.time.Duration;

/**
 * Configuration for SOAP Web Service communication with NIDA
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(NidaProperties.class)
public class WebServiceConfiguration {

    private final NidaProperties nidaProperties;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan(
            "tz.co.itrust.services.nida.soap.tempuri",
            "tz.co.itrust.services.nida.soap.datacontract"
        );
        return marshaller;
    }

    @Bean
    public SaajSoapMessageFactory messageFactory() throws SOAPException {
        MessageFactory msgFactory = MessageFactory.newInstance("SOAP 1.2 Protocol");
        return new SaajSoapMessageFactory(msgFactory);
    }

    @Bean
    public HttpUrlConnectionMessageSender messageSender() {
        HttpUrlConnectionMessageSender messageSender = new HttpUrlConnectionMessageSender();
        messageSender.setConnectionTimeout(
            Duration.ofSeconds(nidaProperties.getService().getTimeout().getConnection())
        );
        messageSender.setReadTimeout(
            Duration.ofSeconds(nidaProperties.getService().getTimeout().getRead())
        );
        return messageSender;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(
            Jaxb2Marshaller marshaller,
            SaajSoapMessageFactory messageFactory,
            HttpUrlConnectionMessageSender messageSender) {
        
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        webServiceTemplate.setDefaultUri(nidaProperties.getService().getBaseUrl());
        webServiceTemplate.setMessageFactory(messageFactory);
        webServiceTemplate.setMessageSender(messageSender);
        
        log.info("Configured WebServiceTemplate with URI: {}", 
                nidaProperties.getService().getBaseUrl());
        
        return webServiceTemplate;
    }
}