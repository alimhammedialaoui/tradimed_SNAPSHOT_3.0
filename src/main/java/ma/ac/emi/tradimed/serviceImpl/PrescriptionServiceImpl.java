package ma.ac.emi.tradimed.serviceImpl;

import ma.ac.emi.tradimed.LV.ReadFile;
import ma.ac.emi.tradimed.service.PrescriptionService;
import ma.ac.emi.tradimed.utility.Builder;
import ma.ac.emi.tradimed.utility.BuilderMed;
import ma.ac.emi.tradimed.utility.Corrector;
import ma.ac.emi.tradimed.utility.Translator;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static ma.ac.emi.tradimed.LV.Analyser.listeErrone;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    public String getCorrection(String entry) throws FileNotFoundException {
        String fr = ResourceUtils.getFile("classpath:data/Fr-Dict.csv").toString();
        Corrector corrector = new Corrector(new Builder().buildDictionary(fr));
        return corrector.getCorrection(entry);

    }

    @Override
    public String getTranslation(String entry) throws FileNotFoundException {
        Builder builder = new Builder();
        BuilderMed builderMed= new BuilderMed();
        //Translator translator = new Translator(builder.buildDictionary("src/main/resources/data/Fr-Dict.csv"),builder.buildDictionary("src/main/resources/data/Ar-Dict.csv"));
        String fr = ResourceUtils.getFile("classpath:data/Fr-Dict.csv").toString();
        String ar = ResourceUtils.getFile("classpath:data/Ar-Dict.csv").toString();
        String med = ResourceUtils.getFile("classpath:data/Book1.csv").toString();
        Translator translator = new Translator(builder.buildDictionary(fr),builder.buildDictionary(ar),builderMed.buildDictionary(med));
        System.out.println(translator.getTranslation(entry));
        return translator.getTranslation(entry);
    }

    @Override
    public HashMap<String ,List<String>> getListeErrones(String sequence, ReadFile file) throws IOException {
        String med = ResourceUtils.getFile("classpath:data/Ar-Dict.csv").toString();
        return listeErrone(sequence, file, med);
    }


}
