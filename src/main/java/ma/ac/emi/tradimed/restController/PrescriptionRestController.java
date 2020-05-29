package ma.ac.emi.tradimed.restController;

import ma.ac.emi.tradimed.LV.ReadFile;
import ma.ac.emi.tradimed.entity.Prescription;
import ma.ac.emi.tradimed.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/restApi")
public class PrescriptionRestController {

    @Autowired
    private PrescriptionService service;

    @GetMapping("/corr/{str}")
    public String correct(@PathVariable String str) throws FileNotFoundException {
        return service.getCorrection(str);
    }

    @PostMapping("/correct")
    public Prescription getCorrection(@RequestBody Prescription prescription) throws FileNotFoundException {
        String correction = service.getCorrection(prescription.getOriginalText());
        prescription.setCorrectedText(correction);
        return prescription;
    }

    @PostMapping("/translate")
    public Prescription getTranslation(@RequestBody Prescription prescription) throws IOException {

        //String correction = service.getCorrection(prescription.getOriginalText());
        String translation = service.getTranslation(prescription.getOriginalText());

        //prescription.setCorrectedText(correction);
        prescription.setTranslatedText(translation);

        return prescription;
    }

    @PostMapping("/lv")
    public Prescription getLV(@RequestBody Prescription prescription) throws IOException {
        HashMap<String, List<String>> stringListHashMap = service.getListeErrones(prescription.getOriginalText(),new ReadFile());
        prescription.setLvText(stringListHashMap.toString());
        return prescription;
    }



}
