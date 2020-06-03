package ma.ac.emi.tradimed.controller;

import ma.ac.emi.tradimed.LV.ReadFile;
import ma.ac.emi.tradimed.entity.Prescription;
import ma.ac.emi.tradimed.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/prescriptions")
@CrossOrigin
public class PrescriptionController {

    private Prescription thePrescription;

    @PostConstruct
    public void initiate(){
        thePrescription = new Prescription();
    }

    @Autowired
    private PrescriptionService service;

    @GetMapping("/showTools")
    public ModelAndView showToolsPage(){
        ModelAndView modelAndView = new ModelAndView("analyser");
        modelAndView.addObject("prescription",thePrescription);
        return modelAndView;
    }

    @PostMapping("/translate")
    public String getTranslation(@ModelAttribute("prescription")Prescription prescription) throws IOException {
        thePrescription = prescription;
        HashMap<String, List<String>> stringListHashMap = service.getListeErrones(prescription.getOriginalText(),new ReadFile());
        String correction = service.getCorrection(thePrescription.getOriginalText());
        String translation = service.getTranslation(thePrescription.getOriginalText());

        //System.out.println(stringListHashMap.toString());
        //prescription.setLvText(stringListHashMap);
        prescription.setCorrectedText(correction);
        prescription.setTranslatedText(translation);
        return "analyser";
//        return "redirect:/prescriptions/showTools";
    }


}
