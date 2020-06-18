package ma.ac.emi.tradimed.utility;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Translator {

    private final Map<String, String> dictionaryAr;
    private final Map<String,String> dictionnaryMed;

    public Translator(Map<String, String> dictionaryAr, Map<String, String> dictionnaryMed) {

        this.dictionaryAr = dictionaryAr;
        this.dictionnaryMed = dictionnaryMed;
    }


    public String getTranslation(String entry) {


        String result = "";
        boolean isTranslated = false;
        boolean endsWithComma=false;

        String[] elementsParent = entry.split("\n");

        for (String ligne : elementsParent) {
            String line= removeAccent(ligne).replaceAll("\\s+"," ");

            StringBuilder lineresult = new StringBuilder();
            StringBuilder trad = new StringBuilder();
            List<String> mots = Arrays.asList(line.split("\\s"));
            for (int j = 0; j < mots.size(); j++) {
                if(printSpecialChar(mots.get(j)).get(1).equals("true")){
                    lineresult.append(printSpecialChar(mots.get(j)).get(0)).append(" ");
                    continue;
                }
                isTranslated = false;
                endsWithComma=false;
                trad.append(removeAccent(mots.get(j)));
                for (String origin : dictionaryAr.keySet()) {
                    if (trad.toString().toLowerCase().equals(removeAccent(origin).toLowerCase())) {
                        lineresult.append(dictionaryAr.get(origin));
                        if(endsWithComma){
                            lineresult.append("،").append(" ");
                        }else{
                            lineresult.append(" ");
                        }
                        isTranslated = true;
                        break;
                    }else if(isNumberGreaterThan20(trad.toString())){
                        lineresult.append(trad.toString());
                        isTranslated=true;
                        if(endsWithComma){
                            lineresult.append("،").append(" ");
                            break;
                        }
                        lineresult.append(" ");
                        break;
                    }else if(trad.toString().startsWith(",")){
                        lineresult.append("،").append(trad.substring(0,trad.length()-1)).append(" ");
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
                    }else if(isCommaNumber(trad.toString())){
                        lineresult.append(trad).append(" ");
                        isTranslated=true;
                        break;
                    }
                }
                for (String origin : dictionnaryMed.keySet()) {
                    StringBuilder tradMed = new StringBuilder();
                    if(j<mots.size()-1) {
                        tradMed.append(trad).append(" ").append(mots.get(j+1));
                    }
                    if ((trad+" ").toLowerCase().equals(removeAccent(origin).toLowerCase()) || tradMed.toString().toLowerCase().equals(removeAccent(origin).toLowerCase()+" ") ) {
                        lineresult.append(dictionnaryMed.get(origin)).append(" ");
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
            if(i>=3) isGreater=true;
        } catch (NumberFormatException e) {
            return false;
        }
        return isGreater;
    }

    private boolean isCommaNumber(String s){
        return s.contains(",");
    }

    private List printSpecialChar(String entry){
        List<String> specialCharacters = Arrays.asList(")","-","+","%","/",":");
        if(entry.length()==1 && specialCharacters.contains(entry) ){
            return Arrays.asList(entry,"true");
        }
        if(entry.length()>=2) {
            String specialChar = entry.substring(entry.length()-1);
            if(specialCharacters.contains(specialChar)){
                if(specialChar.equals(")")){
                    return Arrays.asList(entry.substring(0,entry.length()-1)+")","true");
                }
                return Arrays.asList(entry.substring(0,entry.length()-1)+specialChar,"true");
            }
        }

        return Arrays.asList(entry,"false");
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
