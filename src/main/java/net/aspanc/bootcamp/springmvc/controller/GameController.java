package net.aspanc.bootcamp.springmvc.controller;

import lombok.AccessLevel;
import lombok.Getter;
import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.data.SteamNewsData;
import net.aspanc.bootcamp.springmvc.facade.GameFacade;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
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
            model.addAttribute("messageError", "controller.messagerror.notfoundgame");
            return "error";
        }
    }

    @RequestMapping(value = "/game/delete/{gameId}", method = RequestMethod.GET)
    public String deleteGameById(@PathVariable Long gameId, RedirectAttributes attributes) {
        try {
            getGameFacade().remove(gameId);
            attributes.addFlashAttribute("deleteMessage", "controller.delete.success");
        } catch (EmptyResultDataAccessException ex) {
            attributes.addFlashAttribute("deleteMessage", "controller.delete.failed");
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String showGameListByQuery(@RequestParam(value = "q") String query, Model model) {
        model.addAttribute("gameList",
                getGameFacade().findByQuery(query));
        model.addAttribute("q", query);
        return "index";
    }

    @RequestMapping(value = "/game/new", method = RequestMethod.GET)
    public String showSaveGamePage(Model model) {
        model.addAttribute("game", new GameData());
        model.addAttribute("title", "controller.title.addgame");
        return "savegame";
    }

    @RequestMapping(value = "/game/new", method = RequestMethod.POST)
    public String createNewGame(@Valid @ModelAttribute("game") GameData game,
                                BindingResult bindingResult, Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "controller.title.addgame");
            return "savegame";
        }
        Long id = getGameFacade().save(game).getId();
        attributes.addFlashAttribute("saveMessage", "controller.save.success");
        return "redirect:/game/" + id;
    }

    @RequestMapping(value = "/game/edit/{gameId}", method = RequestMethod.GET)
    public String showSaveGamePage(@PathVariable Long gameId, Model model) {
        try {
            model.addAttribute("game", getGameFacade().findOne(gameId));
            model.addAttribute("title", "controller.title.editgame");
            return "savegame";
        } catch (NoSuchElementException ex) {
            model.addAttribute("messageError", "controller.messagerror.notfoundgame");
            return "error";
        }
    }

    @RequestMapping(value = "/game/edit/{gameId}", method = RequestMethod.POST)
    public String updateGameById(@PathVariable Long gameId, @Valid @ModelAttribute("game") GameData game,
                                 BindingResult bindingResult, Model model, RedirectAttributes attributes) {
        game.setId(gameId);
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "controller.title.editgame");
            return "savegame";
        }
        getGameFacade().save(game);
        attributes.addFlashAttribute("saveMessage", "controller.edit.success");
        return "redirect:/game/" + gameId;
    }

    @ResponseBody
    @RequestMapping(value = "/game/steam/details/{steamId}", method = RequestMethod.GET)
    public String getGameDetailsBySteamID(@PathVariable Integer steamId) {
        try {
            return gameFacade.getGameDetailsBySteamID(steamId);
        } catch (NullPointerException ex) {
            return "https://image.shutterstock.com/z/stock-vector-no-image-available-sign-absence-of-image-373243873.jpg";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/game/steam/news/{steamId}", method = RequestMethod.GET)
    public List<SteamNewsData>  getGameNewsBySteamID(@PathVariable Integer steamId) {
        return getGameFacade().getGameNewsBySteamID(steamId);
    }
}
