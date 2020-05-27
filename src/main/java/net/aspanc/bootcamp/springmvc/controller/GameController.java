package net.aspanc.bootcamp.springmvc.controller;

import lombok.AccessLevel;
import lombok.Getter;
import net.aspanc.bootcamp.springmvc.facade.GameFacade;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@Getter(AccessLevel.PROTECTED)
@Controller
public class GameController {

    private GameFacade gameFacade;

    public GameController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
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

    @RequestMapping(value = "/game/delete/{gameId}", method = RequestMethod.GET)
    public String deleteGameById(@PathVariable Long gameId, RedirectAttributes attributes) {
        try {
            getGameFacade().remove(gameId);
            attributes.addFlashAttribute("deleteMessage", "Juego borrado correctamente");
        } catch (EmptyResultDataAccessException ex) {
            attributes.addFlashAttribute("deleteMesagge", "No se ha podido borrar el juego solicitado");
        }
        return "redirect:/";
    }
}
