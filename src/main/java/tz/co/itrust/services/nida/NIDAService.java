package tz.co.itrust.services.nida;

import tz.co.itrust.services.nida.datacontract.schemas.CIGResponse;
import tz.co.itrust.services.nida.requests.NidaNINPayload;
import tz.co.itrust.services.nida.responses.NidaResponse;
import tz.co.itrust.services.nida.tempuri.RQVerificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NIDAService {
   private static final Logger logger = LoggerFactory.getLogger(NIDAService.class);
   private final NIDAVerificationService nidVerificationService;

   public NIDAService(NIDAVerificationService nidVerificationService) {
      this.nidVerificationService = nidVerificationService;
   }

   public NidaResponse nidaQrRequest(NidaNINPayload nidaPayload) {
      RQVerificationResponse qrResponse = this.nidVerificationService.nidaInquiry(nidaPayload);
      if (qrResponse == null) {
         logger.info("failed to get response from NIDA");
         return null;
      } else {
         NidaResponse result = null;

         try {
            String nidaCode = (String)((CIGResponse)qrResponse.getRQVerificationResult().getValue()).getCode().getValue();
            logger.info("Nida response code {}", nidaCode);
            if (nidaCode.equals("122") || nidaCode.equals("130") || nidaCode.equals("131")) {
               logger.info("NIDA verification failed");
            }

            result = this.nidVerificationService.decodeNidaResponse(qrResponse);
            if (result.previousAnswecode != null && result.previousAnswecode.equals("124")) {
               logger.error("Wrong previous answer");
            } else if (result.previousAnswecode != null && result.previousAnswecode.equals("123")) {
               logger.info("Correct previous answer");
            } else if (nidaCode.equals("120")) {
               logger.info("Valid NIN number and has next question");
            } else if (nidaCode.equals("00")) {
               logger.info("NIDA KYC SUCCESS");
            } else if (nidaCode.equals("132")) {
               logger.info("NIN not found");
            }
         } catch (Exception var5) {
            logger.error("Could not get nida response {}", var5.getMessage());
         }

         return result;
      }
   }

}
