package mahdziak.cars.saloncars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/")
public class PublicController {

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }
}
