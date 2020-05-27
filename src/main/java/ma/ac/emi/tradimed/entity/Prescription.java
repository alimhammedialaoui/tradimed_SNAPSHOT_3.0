package ma.ac.emi.tradimed.entity;

public class Prescription {

    private String originalText;

    private String correctedText;

    private String lvText;

    private String translatedText;

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getCorrectedText() {
        return correctedText;
    }

    public void setCorrectedText(String correctedText) {
        this.correctedText = correctedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getLvText() {return lvText;}

    public void setLvText(String lvText) {this.lvText = lvText;}

    public Prescription() {
    }
}
