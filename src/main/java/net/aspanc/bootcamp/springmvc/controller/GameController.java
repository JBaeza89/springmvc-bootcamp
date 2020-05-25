package net.aspanc.bootcamp.springmvc.controller;

import net.aspanc.bootcamp.springmvc.facade.GameFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.NoSuchElementException;

@Controller
public class GameController {

    private GameFacade gameFacade;

    public GameController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    public GameFacade getGameFacade() {
        return gameFacade;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex(Model model) {
        model.addAttribute("gameList", getGameFacade().findAll());
        return "index";
    }

    @RequestMapping(value = "/game/{gameId}", method = RequestMethod.GET)
    public String showGameById(@PathVariable Long gameId, Model model) {
        try {
            model.addAttribute("game", getGameFacade().findOne(gameId));
            return "game";
        } catch (NoSuchElementException ex) {
            model.addAttribute("messageError", "No se ha encontrado juego con esa Id");
            return "error";
        }

    }
}
