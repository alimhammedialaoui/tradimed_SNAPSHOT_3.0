//package ma.ac.emi.tradimed;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class ConfigVariables extends FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration{
//
//    @Value("${server.port}")
//    private String myProp;
//
//    @Override
//    public FreeMarkerConfigurer freeMarkerConfigurer() {
//        FreeMarkerConfigurer configurer = super.freeMarkerConfigurer();
//
//        Map<String, Object> sharedVariables = new HashMap<>();
//        sharedVariables.put("myProp", myProp);
//        configurer.setFreemarkerVariables(sharedVariables);
//
//        return configurer;
//    }
//}
