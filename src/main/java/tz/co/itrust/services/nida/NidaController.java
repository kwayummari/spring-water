package tz.co.itrust.services.nida;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tz.co.itrust.services.nida.datacontract.schemas.BIOService;
import tz.co.itrust.services.nida.requests.BiometricDto;
import tz.co.itrust.services.nida.requests.BiometricRequest;
import tz.co.itrust.services.nida.requests.NidaNINPayload;
import tz.co.itrust.services.nida.requests.NidaRequest;
import tz.co.itrust.services.nida.responses.NidaResponse;

@RestController
@RequestMapping({"nida"})
public class NidaController {

   private final NIDAService nidaService;

   private final BIOService bioService;

   public NidaController(NIDAService nidaService, BIOService bioService) {
      this.nidaService = nidaService;
       this.bioService = bioService;
   }

   @PostMapping({"/enquiry"})
   public NidaResponse enquiry(@RequestBody NidaRequest nidaRequest) {
      NidaNINPayload nidaNINPayload = new NidaNINPayload(nidaRequest.getNin(), nidaRequest.getRqCode(), nidaRequest.getQnAnsw());
      return this.nidaService.nidaQrRequest(nidaNINPayload);
   }

   @PostMapping({"/enquiry-bio"})
   public NidaResponse enquiryBio(@RequestBody BiometricDto nidaRequest) {
      BiometricRequest nidaNINPayload = new BiometricRequest(nidaRequest.getNin(), nidaRequest.getFingerCode(), nidaRequest.getFingerPrintImage());
      return this.bioService.nidaRequest(nidaNINPayload);
   }
}
