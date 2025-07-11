package tz.co.itrust.services.nida.datacontract.schemas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tz.co.itrust.services.nida.BioVerificationService;
import tz.co.itrust.services.nida.requests.BiometricRequest;
import tz.co.itrust.services.nida.responses.NidaResponse;
import tz.co.itrust.services.nida.tempuri.VerificationResponse;

@Service
public class BIOService {

   private static final Logger logger = LoggerFactory.getLogger(BIOService.class);

   private final BioVerificationService nidVerificationService;

   public BIOService(BioVerificationService nidVerificationService) {
      this.nidVerificationService = nidVerificationService;
   }

   public NidaResponse nidaRequest(BiometricRequest nidaPayload) {
      VerificationResponse qrResponse = this.nidVerificationService.inquiry(nidaPayload);
      if (qrResponse == null) {
         logger.info("failed to get response from NIDA");
         return null;
      } else {
         NidaResponse result = null;

         try {
            String nidaCode = (String)((CIGResponse)qrResponse.getBiometricVerificationResult().getValue()).getCode().getValue();

            logger.info("Nida response code {}", nidaCode);

            if (nidaCode.equals("122") || nidaCode.equals("130") || nidaCode.equals("131")) {
               logger.info("NIDA verification failed");
            }

            result = this.nidVerificationService.decodeResponse(qrResponse);

         } catch (Exception var5) {
            logger.error("Could not get nida response {}", var5.getMessage());
         }

         return result;
      }
   }
}
