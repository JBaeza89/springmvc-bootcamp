package net.aspanc.bootcamp.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", params = {"error"}, method = RequestMethod.GET)
    public String showLoginPageWithErrors(Model model) {
        model.addAttribute("messageError", "controller.messagerror.wrongcredentials");
        return "login";
    }

}

