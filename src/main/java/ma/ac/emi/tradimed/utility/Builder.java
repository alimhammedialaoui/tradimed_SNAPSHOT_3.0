package ma.ac.emi.tradimed.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Builder {

    public Map<String, String> buildDictionary(String path) {
        Map<String, String> dictionary = new HashMap<>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                List<String> term = Arrays.asList(line.split(cvsSplitBy));
                String ligne = term.toString();
                dictionary.put(term.get(0), term.get(1));
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


///////////////////////////////////////////////////////////////


//    public List<String[]> buildDictionary (String path) {
//        List<String[]> dictionary = new ArrayList<>();
//        String csvFilePath = path;
//        BufferedReader br = null;
//        String line = "";
//        String cvsSplitBy = ",";
//        try {
//            br = new BufferedReader(new FileReader(csvFilePath));
//            while ((line = br.readLine()) != null) {
//                String[] term = line.split(cvsSplitBy);
//                dictionary.add(term);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return dictionary;
//    }
