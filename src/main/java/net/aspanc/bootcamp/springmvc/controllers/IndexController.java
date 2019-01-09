package net.aspanc.bootcamp.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static net.aspanc.bootcamp.springmvc.util.Constants.Pages.INDEX_PAGE;

@Controller
public class IndexController {

    @GetMapping("/")
    public String get() {
        return INDEX_PAGE;
    }
}
