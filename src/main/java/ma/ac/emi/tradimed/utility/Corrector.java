package ma.ac.emi.tradimed.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class Corrector {

    private final Map<String, List<String>> dictionaryFr;


    public Corrector(Map<String, List<String>> dictionaryFr) {
        this.dictionaryFr = dictionaryFr;

    }

    private List keyof(String entry) {
        for (String line : dictionaryFr.keySet()) {
            if (wordinList(entry, dictionaryFr.get(line))) {
                return Arrays.asList(line + " ", true);
            }
        }
        return Arrays.asList(entry + " ", false);
    }

    private boolean wordinList(String entry, List<String> list) {
        list.replaceAll(String::toLowerCase);
        List<String> stringList = new ArrayList<>();
        for (String e : list) {
            stringList.add(removeAccent(e));
        }
        return stringList.contains(removeAccent(entry.toLowerCase()));
    }


    public String Correct(String entry) {
        String[] elementsParent = entry.split("\n");
        StringBuilder result = new StringBuilder();

        for (String ligne : elementsParent) {
            String line = ligne.replaceAll("\\s+", " ");
            StringBuilder lineresult = new StringBuilder();
            StringBuilder trad = new StringBuilder();
            List<String> mots = Arrays.asList(line.split("\\s"));
            trad.append(correctLine(mots)).append("\n");
            result.append(trad);
        }
        return result.toString();
    }

    private String correctLine(List<String> words) {
        StringBuilder correctedLine = new StringBuilder();
        String correctedWord = "";
        StringBuilder maxWord = new StringBuilder();
        String save = "";
        boolean isTranslated = false;
        boolean shouldbreak = false;
        boolean endsWithComma=false;
        int j = 0;
        int i = 0;
        while (i < words.size()) {
            endsWithComma=false;
            isTranslated = false;
            maxWord.append(words.get(i));
            if (isNumberGreaterThan20(maxWord.toString())) {
                correctedLine.append(maxWord).append(" ");
                maxWord = new StringBuilder();
                i++;
                continue;
            }
            if(maxWord.toString().endsWith(",")){
                String string = maxWord.substring(0,maxWord.length()-1);
                maxWord =new StringBuilder();
                maxWord.append(string);
                endsWithComma=true;
            }
            for (j = i + 1; j < words.size(); j++) {
                save = words.get(j);
                correctedWord = keyof(maxWord.toString()).get(0).toString();
                if (keyof(maxWord.toString()).get(1).toString().equals("true")) {
                    isTranslated = true;
                    break;
                }
                maxWord.append(" ");
                maxWord.append(save);
                save = "";
            }
            correctedWord = keyof(maxWord.toString()).get(0).toString();
            if(endsWithComma){
                correctedWord = correctedWord+",";
            }
            if (keyof(maxWord.toString()).get(1).toString().equals("true")) {
                isTranslated = true;
            }
            if (keyof(maxWord.toString()).get(1).toString().equals("true")) {
                i = j;
                //correctedWord = correctedWord+" "+save;
            }
//            if (correctedWord.equals("")) {
//                correctedWord = keyof(maxWord.toString()).get(0).toString();
//            }
            if (isTranslated) {
                correctedLine.append(correctedWord).append(" ");
                save = "";
                maxWord = new StringBuilder();
                continue;
            } else {
                try {
                    correctedLine.append(words.get(i));
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
            correctedLine.append(save).append(" ");
            save = "";
            maxWord = new StringBuilder();
            i++;

        }
        return correctedLine.toString();
    }

    private String removeAccent(String s) {
        String strTemp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(strTemp).replaceAll("");
    }

    public static boolean isNumberGreaterThan20(String num) {
        boolean isGreater = false;
        if (num == null) return false;
        try {
            int i = Integer.parseInt(num);
            if (i >= 1) isGreater = true;
        } catch (NumberFormatException e) {
            return false;
        }
        return isGreater;
    }

}

//
//    public String getCorrection(String entry) {
//
//
//        String result = "";
//        String array = "";
//        String test = "";
//        String origine="";
//        String ligneresult="";
//        boolean isCorrected = false;
//
//        String[] elementsParent = entry.split("\n");
//
//        for (String ligne : elementsParent) {
//            String line=ligne.replaceAll("\\s+"," ");
//            StringBuilder lineresult = new StringBuilder();
//            StringBuilder trad = new StringBuilder();
//            List<String> mots = Arrays.asList(line.split("\\s"));
//
//            for (int j = 0; j < mots.size(); j++) {
//                isCorrected = false;
//                trad.replace(0,trad.length(),mots.get(j));
//                int counter = 0;
//
//                for (String origin : dictionaryFr.keySet()) {
//                    counter++;
//                    origine=origin;
//                    if (trad.toString().toLowerCase().equals(origin.toLowerCase())) {
//                        lineresult.append(dictionaryFr.get(origin)).append(" ");
//                        isCorrected = true;
//                        break;
//                    }
//                }
//                if(!trad.toString().equals(origine) && !isCorrected){
//                    lineresult.append(trad).append(" ");
//                }
//                if (isCorrected) {
//                    trad = new StringBuilder();
//                }else {
//                    trad.append(" ");
//                }
//            }
//
//            result = result + lineresult + "\n";
//        }
//
//
//        return result;
//    }
