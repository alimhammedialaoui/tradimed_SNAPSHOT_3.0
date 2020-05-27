//package ma.ac.emi.tradimed.controller.MessagesController;
//
//import ma.ac.emi.tradimed.entity.Contact.Message;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//
//@Controller
//public class MessageController {
//
//    private Message message;
//
//    @PostConstruct
//    public void inintiate() {
//        this.message = new Message();
//    }
//
////    @Autowired
////    MessagesService messagesService;
//
//
//
//    @PostMapping("/messagePost")
//    public String postMessage(@ModelAttribute("mes") Message message) throws IOException {
//        //messagesService.save(message);
//        return "redirect:/contact";
//    }
//
//}
