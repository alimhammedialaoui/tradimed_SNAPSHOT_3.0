package ma.ac.emi.tradimed.LV;

import java.util.List;

public class FileCSV {
    private String origine;
    private List<String> listeWord;


    public FileCSV(String origine, List<String> listeWord) {
        this.origine = origine;
        this.listeWord = listeWord;
    }

    public FileCSV() {
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public List<String> getListeWord() {
        return listeWord;
    }

    public void setListeWord(List<String> listeWord) {
        this.listeWord = listeWord;
    }


}
