package ma.ac.emi.tradimed.serviceImpl;

import ma.ac.emi.tradimed.LV.ReadFile;
import ma.ac.emi.tradimed.service.PrescriptionService;
import ma.ac.emi.tradimed.utility.Builder;
import ma.ac.emi.tradimed.utility.BuilderMed;
import ma.ac.emi.tradimed.utility.Corrector;
import ma.ac.emi.tradimed.utility.Translator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static ma.ac.emi.tradimed.LV.Analyser.listeErrone;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    public String getCorrection(String entry){
        Corrector corrector = new Corrector(new Builder().buildDictionary("src/main/resources/data/Fr-Dict.csv"));
        return corrector.getCorrection(entry);

    }

    @Override
    public String getTranslation(String entry) {
        Builder builder = new Builder();
        BuilderMed builderMed= new BuilderMed();
        //Translator translator = new Translator(builder.buildDictionary("src/main/resources/data/Fr-Dict.csv"),builder.buildDictionary("src/main/resources/data/Ar-Dict.csv"));

        Translator translator = new Translator(builder.buildDictionary("src/main/resources/data/Fr-Dict.csv"),builder.buildDictionary("src/main/resources/data/Ar-Dict.csv"),builderMed.buildDictionary("src/main/resources/data/Book1.csv"));
        System.out.println(translator.getTranslation(entry));
        return translator.getTranslation(entry);
    }

    @Override
    public HashMap<String ,List<String>> getListeErrones(String sequence, ReadFile file) throws IOException {
        String path="src/main/resources/data/LV.csv";
        return listeErrone(sequence, file, path);
    }


}
