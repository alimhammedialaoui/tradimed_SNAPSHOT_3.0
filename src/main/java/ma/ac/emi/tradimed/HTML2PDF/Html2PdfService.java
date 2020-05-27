package ma.ac.emi.tradimed.HTML2PDF;

import org.springframework.core.io.InputStreamResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface Html2PdfService {
    InputStreamResource generatetextTranslated(Map<String,Object> date) throws IOException;
}
