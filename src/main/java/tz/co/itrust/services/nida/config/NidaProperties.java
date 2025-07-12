package tz.co.itrust.services.nida.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Data
@Validated
@ConfigurationProperties(prefix = "nida")
public class NidaProperties {

    @Valid
    private Service service = new Service();
    
    @Valid
    private Security security = new Security();

    @Data
    public static class Service {
        
        @NotBlank(message = "NIDA service ID is required")
        private String id = "1";
        
        @NotBlank(message = "NIDA user ID is required")
        private String userId = "IMAAN";
        
        @NotBlank(message = "NIDA base URL is required")
        private String baseUrl = "https://nacer01/TZ_CIG/GatewayService.svc";
        
        @Valid
        private Timeout timeout = new Timeout();
    }

    @Data
    public static class Timeout {
        
        @Positive(message = "Connection timeout must be positive")
        private int connection = 30;
        
        @Positive(message = "Read timeout must be positive")
        private int read = 30;
    }

    @Data
    public static class Security {
        
        @NotBlank(message = "Private key path is required")
        private String privateKeyPath = "classpath:keys/private-key.pem";
        
        @NotBlank(message = "Public key path is required")
        private String publicKeyPath = "classpath:keys/public-key.pem";
        
        @NotBlank(message = "Certificate path is required")
        private String certificatePath = "classpath:keys/nida-certificate.pem";
    }
}