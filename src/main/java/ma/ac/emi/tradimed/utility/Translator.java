package ma.ac.emi.tradimed.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class Translator{

    Logger loggerFactory= LoggerFactory.getLogger(Translator.class);


    private final Map<String, String> dictionaryFr;
    private final Map<String, String> dictionaryAr;
    private final Map<String,String> dictionnaryMed;

    public Translator(Map<String, String> dictionaryFr, Map<String, String> dictionaryAr, Map<String, String> dictionnaryMed) {
        this.dictionaryFr = dictionaryFr;
        this.dictionaryAr = dictionaryAr;
        this.dictionnaryMed = dictionnaryMed;
    }





    public String getTranslation(String entry) {


        String result = "";
        String array = "";
        String test = "";
        boolean isTranslated = false;
        boolean endsWithComma=false;

        String[] elementsParent = entry.split("\n");

        for (String ligne : elementsParent) {
            String line= removeAccent(ligne).replaceAll("\\s+"," ");

            List<List<Integer>> list = new ArrayList<>();
            List<Integer> listIndex = new ArrayList<>();
            StringBuilder lineresult = new StringBuilder();
            StringBuilder trad = new StringBuilder();
            List<String> mots = Arrays.asList(line.split("\\s"));
            String expression = String.join(" ", mots);
            list = isNumber(expression);
            listIndex = makeIndexList(list);
            for (int j = 0; j < mots.size(); j++) {
                isTranslated = false;
                endsWithComma=false;
                trad.append(removeAccent(mots.get(j)));
                //loggerFactory.info("Chaine a traduire : "+trad);
                int counter = 0;
                for (String origin : dictionaryAr.keySet()) {
                    counter++;
                    if (trad.toString().toLowerCase().equals(removeAccent(origin).toLowerCase())) {
                        lineresult.append(dictionaryAr.get(origin));
                        if(endsWithComma){
                            lineresult.append("،").append(" ");
                        }else{
                            lineresult.append(" ");
                        }
//                        expression = expression.substring(j, expression.length());
                        isTranslated = true;
                        break;
                    }else if(isNumberGreaterThan20(trad.toString())){
                        lineresult.append(trad.toString()).append(" ");
                        isTranslated=true;
                        break;
                    }else if(trad.toString().startsWith(",")){
                        lineresult.append(trad.toString()).append("، ");
                        isTranslated=true;
                        break;
                    }else if(trad.toString().endsWith(",")){
                        endsWithComma=true;
                        String string = trad.substring(0,trad.length()-1);
                        trad =new StringBuilder();
                        trad.append(string);
                    } else if(trad.toString().startsWith(" ")){
                        String string = trad.substring(1,trad.length());
                        trad =new StringBuilder();
                        trad.append(string);
                        lineresult.append(" ");
                    }else if(trad.toString().equals(", ")){
                        lineresult.append("،");
                        isTranslated=true;
                        break;
                    }
                }
                for (String origin : dictionnaryMed.keySet()) {
                    counter++;
                    if (trad.toString().equals(removeAccent(origin))) {
                        lineresult.append(dictionnaryMed.get(origin)).append(" ");
//                        expression = expression.substring(j, expression.length());
                        isTranslated = true;
                        break;
                    }
                }
                if (isTranslated) {
                    trad = new StringBuilder();
                }else {
                    trad.append(" ");
                }

            }

            result = result + lineresult + "\n";
        }


        return result;
    }
    public static String removeAccent(String s){
        String strTemp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(strTemp).replaceAll("");
    }

    public static boolean isNumberGreaterThan20(String num){
        boolean isGreater=false;
        if(num==null) return false;
        try{
            int i=Integer.parseInt(num);
            if(i>=20) isGreater=true;
        } catch (NumberFormatException e) {
            return false;
        }
        return isGreater;
    }


    private static List<List<Integer>> isNumber(String line) {
        int index;
        int length;
        int i = 0;

        List<List<Integer>> table = new ArrayList<>();
        String[] tableau = line.split("\\s");
        while (i < tableau.length) {
            List<Integer> list = new ArrayList<Integer>();
            int size = tableau[i].length();
            try {
                Integer.parseInt(tableau[i]);
            } catch (NumberFormatException e) {

                i++;
                continue;
            }

            index = i;
            length = size;
            list.add(Integer.parseInt(tableau[i]));
            list.add(index);
            list.add(length);
            i++;
            table.add(list);
        }

        return table;
    }


    private static List<Integer> makeIndexList(List<List<Integer>> lists) {
        List<Integer> listindex = new ArrayList<>();
        for (List<Integer> list : lists) {
            listindex.add(list.get(1));
        }

        return listindex;
    }

}


//                    while(m<dictionaryMeds.size() && isMed){
//                        array=Arrays.toString(dictionaryMeds.get(m));
//                        for(int s=0;s<=dictionaryMeds.get(m)[0].length();s++){
//                            test=dictionaryMeds.get(m)[0].substring(0, s);
//                            if(dictionaryMeds.get(m)[0].substring(0, s).equals(elements[j])){
//                                System.out.println("Meds  :"+dictionaryMeds.get(m)[0].substring(0,s)+"   str   :" +elements[j]);
//                                trad=dictionaryMeds.get(m)[0].substring(0,s);
//                            }
//                            if (dictionaryMeds.get(m)[0].substring(0,s).equals(elements[j])){
//                                tmp=dictionaryMeds.get(m)[dictionaryMeds.get(m).length-1];
//                                endOfElement=true;
//                                break;
//                            }
//                        }
//                        if(endOfElement) break;
//                        m++;
//                    }


//////////////////////////////////////////////////////////////////////////////////////////

//for(String elementParent:elementsParent){
//        boolean offLine=true;
//        boolean endOfElement=false;
//        boolean isMed=true;
//        System.out.println("\nParent "+elementParent);
//        String[] elements = elementParent.split(" ");
//
//        for(int i=0;i<elements.length;i++){
//        String str="";
//        String tmp ="";
//        for(int j=i;j<elements.length;j++){
//        int m=0;
//        offLine=false;
//        isMed=true;
//        str = str + elements[j];
//        System.out.println("World="+ str+"   i :"+i+"   j  :"+j);
//        for (int k = 0; k < dictionaryAr.size(); k++) {
//        if (dictionaryAr.get(k)[0].equals(str)) {
//        offLine=true;
//        isMed=false;
//        System.out.println("Arabe");
//        tmp = dictionaryAr.get(k)[1];
//        i=j;
//        break;
//        }
//        else if(dictionaryAr.get(k)[0].equals(findKey(str))){
//        offLine=true;
//        isMed=false;
//        System.out.println("Francais");
//        tmp = dictionaryAr.get(k)[1];
//        i=j;
//        break;
//        }
//        }
//
//        str = str +' ';
//        }
//
//        //we append the result stored previously in the tmp variable
//        result = result + tmp+' ';
//        }
//        result = result+"\n";
//        }
//        return result;
//        }
//
//private String findKey(String term){
//        for(int i=0;i<dictionaryFr.size();i++){
//        for(int j=0;j<dictionaryFr.get(i).length;j++){
//        if(term.equals(dictionaryFr.get(i)[j])) return dictionaryFr.get(i)[0];
//        }
//        }
//        return null;


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*OnlineTranslator onlineTranslator = new OnlineTranslator();
        String result= new String() ;
        String[] elements = entry.split(" ");
        for(int i=0;i<elements.length;i++){
            String str="";
            String tmp ="";
            for(int j=i;j<elements.length;j++){
                str = str + elements[j];
                for (int k = 0; k < dictionaryAr.size(); k++) {
                    if (dictionaryAr.get(k)[0].equals(str)) {
                        tmp = dictionaryAr.get(k)[1];
                        i=j;
                    }
                    else if(dictionaryAr.get(k)[0].equals(findKey(str))){
                        tmp = dictionaryAr.get(k)[1];
                        i=j;
                    }
                }
                str = str +' ';
            }
            //here we add words for which we didn't find translation
            if(tmp.equals("")) {
                if(onlineTranslator.getOnlineTranslation(elements[i]).equals("")) {
                    result = result+elements[i]+' ';
                }
                else result = result+onlineTranslator.getOnlineTranslation(elements[i])+' ';
            }
            //we append the result stored previously in the tmp variable
            else result = result + tmp+' ';
        }
        return result;*/




//                    while(m<dictionaryMeds.size() && isMed){
//                        array=Arrays.toString(dictionaryMeds.get(m));
//                        for(int s=0;s<=dictionaryMeds.get(m)[0].length();s++){
//                            test=dictionaryMeds.get(m)[0].substring(0, s);
//                            if(dictionaryMeds.get(m)[0].substring(0, s).equals(elements[j])){
//                                System.out.println("Meds  :"+dictionaryMeds.get(m)[0].substring(0,s)+"   str   :" +elements[j]);
//                                trad=dictionaryMeds.get(m)[0].substring(0,s);
//                            }
//                            if (dictionaryMeds.get(m)[0].substring(0,s).equals(elements[j])){
//                                tmp=dictionaryMeds.get(m)[dictionaryMeds.get(m).length-1];
//                                endOfElement=true;
//                                break;
//                            }
//                        }
//                        if(endOfElement) break;
//                        m++;
//                    }
