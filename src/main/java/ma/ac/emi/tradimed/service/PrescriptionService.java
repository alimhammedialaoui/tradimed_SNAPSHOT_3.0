package ma.ac.emi.tradimed.service;

import ma.ac.emi.tradimed.LV.ReadFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface PrescriptionService {

    public String getCorrection(String entry) throws FileNotFoundException;
    public String getTranslation(String entry) throws FileNotFoundException;
    public HashMap<String, List<String >> getListeErrones(String sequence, ReadFile file) throws IOException;
}
