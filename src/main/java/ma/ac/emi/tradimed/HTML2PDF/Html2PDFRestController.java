package ma.ac.emi.tradimed.HTML2PDF;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

@Controller
public class Html2PDFRestController {
    private final Html2PdfService html2PdfService;

    public Html2PDFRestController(Html2PdfService html2PdfService) {
        this.html2PdfService = html2PdfService;
    }
    @PostMapping(value = "/html2pdf",produces = "application/pdf")
    public ResponseEntity html2pdf(@RequestBody Map<String,Object> data) throws IOException {
        InputStreamResource resource = html2PdfService.generatetextTranslated(data);
        if(resource!=null){
            return ResponseEntity.ok().body(resource);
        }else{
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
