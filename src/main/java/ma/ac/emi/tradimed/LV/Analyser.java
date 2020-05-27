package ma.ac.emi.tradimed.LV;

import safar.machine_learning.levenshtein.factory.LevenshteinFactory;
import safar.machine_learning.levenshtein.interfaces.ILevenshtein;

import java.io.IOException;
import java.util.*;

public class Analyser {

    public static HashMap<String ,List<String >> listeErrone(String sequence, ReadFile file, String path) throws IOException {
        List<String> listeErrones = new ArrayList<String>();
        HashMap<String ,List<String >> map = new HashMap<String, List<String>>();
        String mots[] = sequence.split("\\s");
        // prendre les meilleures
        file.openFile(path);
        file.readFile();
        file.closeFile();
        for (String word : mots) {
            int cpt = 3;
            List<String> propositions = new ArrayList<String>();
            int isCorrect = 0;
            String array= file.getFile().getListeWord().toString();
            for (String e : file.getFile().getListeWord()) {
                int distance = safarLevenshteinTest(word, e);
                if (distance == 0) {
                    isCorrect = 1;
                } else {
                    if ((distance == 1  ) && cpt>0) {
                        propositions.add(e);
                        cpt--;
                    }
                }
            }
            if (isCorrect == 0 && propositions.size()!=0) {
                map.put(word, propositions);

                listeErrones.add(word);
            }
        }
        return map;
    }


//    public static void main(String[] args) throws IOException {
//        Scanner scnanner = new Scanner(System.in);
//        System.out.print("Saisir un paragraphe :");
//        String sequence = scnanner.nextLine();
//        ReadFile file = new ReadFile();
//        String path = "C:\\Users\\alima\\Documents\\IdeaProjects\\Levenshtein\\Files\\file2.csv";
//        HashMap<String, List<String>> liste = listeErrone(sequence, file, path);
//        for (Map.Entry v : liste.entrySet()) {
//            System.out.print(v + " ");
//        }
//    }

    public static int safarLevenshteinTest(CharSequence lS, CharSequence rS) {

        ILevenshtein safarLevFactory = LevenshteinFactory.getSAFARImplementation();
        int safarLev = safarLevFactory.getLevenshtein(lS, rS);
        return safarLev;

    }

}
