package tz.co.itrust.services.nida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.TimeZone;

/**
 * Main Spring Boot Application for NIDA Service
 * 
 * This service provides identity verification capabilities
 * through Tanzania's National Identification Authority (NIDA)
 * 
 * @author iTrust Team
 * @version 1.0.0
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class NidaServiceApplication {

    public static void main(String[] args) {
        // Set default timezone for Tanzania
        TimeZone tz = TimeZone.getTimeZone("Africa/Dar_es_Salaam");
        TimeZone.setDefault(tz);
        
        SpringApplication.run(NidaServiceApplication.class, args);
    }
}