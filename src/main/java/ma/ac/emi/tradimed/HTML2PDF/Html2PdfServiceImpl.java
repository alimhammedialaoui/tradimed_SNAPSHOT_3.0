package ma.ac.emi.tradimed.HTML2PDF;

import com.itextpdf.html2pdf.HtmlConverter;
import lombok.extern.java.Log;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
@Log
public class Html2PdfServiceImpl implements Html2PdfService {

    private final TemplateEngine templateEngine;

    public Html2PdfServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public InputStreamResource generatetextTranslated(Map<String, Object> date) throws IOException {
        Context context=new Context();
        context.setVariables(date);
        String html= templateEngine.process("analyser",context);
        HtmlConverter.convertToPdf(html,new FileOutputStream("/target/file.pdf"));
        return new InputStreamResource(new FileInputStream("/target/file.pdf"));
    }
}
