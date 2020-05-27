package ma.ac.emi.tradimed.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Corrector {

    private final Map<String, String> dictionaryFr;

    public Corrector(Map<String, String> dictionaryFr) {
        this.dictionaryFr = dictionaryFr;
    }


    public String getCorrection(String entry) {


        String result = "";
        String array = "";
        String test = "";
        String origine="";
        String ligneresult="";
        boolean isCorrected = false;

        String[] elementsParent = entry.split("\n");

        for (String ligne : elementsParent) {
            String line=ligne.replaceAll("\\s+"," ");
            StringBuilder lineresult = new StringBuilder();
            StringBuilder trad = new StringBuilder();
            List<String> mots = Arrays.asList(line.split("\\s"));

            for (int j = 0; j < mots.size(); j++) {
                isCorrected = false;
                trad.replace(0,trad.length(),mots.get(j));
                int counter = 0;

                for (String origin : dictionaryFr.keySet()) {
                    counter++;
                    origine=origin;
                    if (trad.toString().toLowerCase().equals(origin.toLowerCase())) {
                        lineresult.append(dictionaryFr.get(origin)).append(" ");
                        isCorrected = true;
                        break;
                    }
                }
                if(!trad.toString().equals(origine) && !isCorrected){
                    lineresult.append(trad).append(" ");
                }
                if (isCorrected) {
                    trad = new StringBuilder();
                }else {
                    trad.append(" ");
                }
            }

            result = result + lineresult + "\n";
        }


        return result;
    }

}
