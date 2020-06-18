package ma.ac.emi.tradimed.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuilderMed {

    public Map<String,String> buildDictionary (String path) {
        Map<String,String> dictionary = new HashMap<>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                List<String > term = Arrays.asList(line.split(cvsSplitBy));
                String ligne =term.toString();
                List<String> firstColumn = Arrays.asList(term.get(0).split(" "));
                StringBuilder nommedicament = new StringBuilder();
                for(String e : firstColumn){
                    try{
                        Double.parseDouble(e);
                        break;
                    } catch (NumberFormatException numberFormatException) {
                        if(e.contains(",")){
                            break;
                        }
                        nommedicament.append(e).append(" ");
                    }

                }
                //nommedicament.append(firstColumn.get(0));
                //System.out.println(nommedicament);
                dictionary.put(nommedicament.toString().toLowerCase(),term.get(term.size()-1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        System.out.println("Size : "+ dictionary.size()+"\n\n");
//        for(String mot :dictionary.keySet()){
//            System.out.println("Key :"+ mot+", Value :"+dictionary.get(mot)+"\n");
//        }
        return dictionary;
    }


}
