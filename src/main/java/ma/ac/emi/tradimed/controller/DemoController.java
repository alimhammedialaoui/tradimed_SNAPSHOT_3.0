package ma.ac.emi.tradimed.controller;

import ma.ac.emi.tradimed.entity.Contact.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/")
public class DemoController {

    private Message message;

    @PostConstruct
    public void inintiate() {
        this.message = new Message();
    }

//    @Autowired
//    MessagesService messagesService;




    @GetMapping("/welcome")
    public String welcomePage() {
        return "welcome";
    }

    @GetMapping("/download")
    public String downloadPage(){
        return "downloads";
    }

    @GetMapping("/team")
    public String teamPage(){
        return "team";
    }

    /*@GetMapping("/contact")
    public String contactPage(){
        return "contact";
    }*/


    @GetMapping("/contact")
    public ModelAndView contactPage(){
        ModelAndView modelAndView= new ModelAndView("contact");
        modelAndView.addObject("mes",message);
        return modelAndView;
    }

}
