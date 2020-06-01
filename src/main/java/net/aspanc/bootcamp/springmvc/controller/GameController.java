package net.aspanc.bootcamp.springmvc.controller;

import lombok.AccessLevel;
import lombok.Getter;
import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.facade.GameFacade;
import net.aspanc.bootcamp.springmvc.validator.GameDataValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.NoSuchElementException;

@Getter(AccessLevel.PROTECTED)
@Controller
public class GameController {

    private GameFacade gameFacade;

    private GameDataValidator gameDataValidator;

    private MessageSource messageSource;

    public GameController(GameFacade gameFacade, GameDataValidator gameDataValidator, MessageSource messageSource) {
        this.gameFacade = gameFacade;
        this.gameDataValidator = gameDataValidator;
        this.messageSource = messageSource;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(getGameDataValidator());
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
            model.addAttribute("messageError",
                    messageSource.getMessage("controller.messagerror.notfoundgame",
                            null, LocaleContextHolder.getLocale()));
            return "error";
        }
    }

    @RequestMapping(value = "/game/delete/{gameId}", method = RequestMethod.GET)
    public String deleteGameById(@PathVariable Long gameId, RedirectAttributes attributes) {
        try {
            getGameFacade().remove(gameId);
            attributes.addFlashAttribute("deleteMessage",
                    messageSource.getMessage("controller.delete.success",
                            null, LocaleContextHolder.getLocale()));
        } catch (EmptyResultDataAccessException ex) {
            attributes.addFlashAttribute("deleteMessage",
                    messageSource.getMessage("controller.delete.failed",
                            null, LocaleContextHolder.getLocale()));
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
        model.addAttribute("title",
                messageSource.getMessage("controller.title.addgame",
                        null, LocaleContextHolder.getLocale()));
        return "savegame";
    }

    @RequestMapping(value = "/game/new", method = RequestMethod.POST)
    public String createNewGame(@Valid @ModelAttribute("game") GameData game,
                                BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title",
                    messageSource.getMessage("controller.title.addgame",
                            null, LocaleContextHolder.getLocale()));
            return "savegame";
        }
        Long id = getGameFacade().save(game).getId();
        return "redirect:/game/" + id;
    }

    @RequestMapping(value = "/game/edit/{gameId}", method = RequestMethod.GET)
    public String showSaveGamePage(@PathVariable Long gameId, Model model) {
        try {
            model.addAttribute("game", getGameFacade().findOne(gameId));
            model.addAttribute("title",
                    messageSource.getMessage("controller.title.editgame",
                            null, LocaleContextHolder.getLocale()));
            return "savegame";
        } catch (NoSuchElementException ex) {
            model.addAttribute("messageError",
                    messageSource.getMessage("controller.messagerror.notfoundgame",
                            null, LocaleContextHolder.getLocale()));
            return "error";
        }
    }

    @RequestMapping(value = "/game/edit/{gameId}", method = RequestMethod.POST)
    public String updateGameById(@PathVariable Long gameId, @Valid @ModelAttribute("game") GameData game,
                                 BindingResult bindingResult, Model model) {
        game.setId(gameId);
        if (bindingResult.hasErrors()) {
            model.addAttribute("title",
                    messageSource.getMessage("controller.title.editgame",
                            null, LocaleContextHolder.getLocale()));
            return "savegame";
        }
        getGameFacade().save(game);
        return "redirect:/game/" + gameId;
    }
}
