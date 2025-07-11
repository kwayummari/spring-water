package tz.co.itrust.services.nida;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.TimeZone;

@SpringBootApplication
public class NidaServiceApplication {

    @Value("${timezone.offset:240000}")
    private String TIMEZONE_OFFSET;

    public static void main(String[] args) {

        TimeZone tz = TimeZone.getTimeZone("Africa/Dar_es_Salaam");
        tz.setRawOffset(10980000);
        TimeZone.setDefault(tz);

        try {
            disableHttps();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        SpringApplication.run(NidaServiceApplication.class, args);
    }

    @Bean
    public Jaxb2Marshaller marshaller() throws Exception{
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths("tz.co.itrust.services.nida.tempuri");
        return marshaller;
    }

    private static void disableHttps() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {

                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }


                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                        // TODO Auto-generated method stub

                    }

                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

    }
}
