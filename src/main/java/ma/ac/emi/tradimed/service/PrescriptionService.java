package ma.ac.emi.tradimed.service;

import ma.ac.emi.tradimed.LV.ReadFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface PrescriptionService {

    public String getCorrection(String entry);
    public String getTranslation(String entry);
    public HashMap<String, List<String >> getListeErrones(String sequence, ReadFile file) throws IOException;
}
