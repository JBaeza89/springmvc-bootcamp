package net.aspanc.bootcamp.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameController {

    @RequestMapping
    public String showIndex() {

        return "index";
    }
}
