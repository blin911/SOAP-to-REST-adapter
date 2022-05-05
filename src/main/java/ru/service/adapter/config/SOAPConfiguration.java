package ru.service.adapter.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.service.adapter.client.*;

@Configuration
public class SOAPConfiguration {

    private static final String RU_SERVICE_ADAPTER_WSDL = "ru.service.adapter.wsdl";

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(RU_SERVICE_ADAPTER_WSDL);
        return marshaller;

    }

    @Bean
    public CalculatorSOAPClient calculatorSOAPClient(Jaxb2Marshaller marshaller) {
        CalculatorSOAPClient client = new CalculatorSOAPClient();
        client.setDefaultUri("http://www.dneonline.com/calculator.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
