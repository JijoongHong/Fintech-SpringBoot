package fintech.spring.controller;

import fintech.spring.entity.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class HelloController {

    @GetMapping("hello-mvc")
    public String helloTemplate(String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }//실행 중 build하면 자동으로 Restart

    @PostConstruct
    public void postConstruct(){
        log.info("I am already constructed");
    }

    @PreDestroy
    public void preDestroy(){
        log.info("I will be destroyed");
    }
}
