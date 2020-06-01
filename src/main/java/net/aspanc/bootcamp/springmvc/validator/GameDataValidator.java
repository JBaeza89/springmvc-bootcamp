package net.aspanc.bootcamp.springmvc.validator;

import lombok.AccessLevel;
import lombok.Getter;
import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.facade.GameFacade;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter(AccessLevel.PROTECTED)
@Component
public class GameDataValidator implements Validator {

    private final GameFacade gameFacade;

    public GameDataValidator(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(GameData.class);
    }

    private boolean gameTitleIsNullOrEmpty(String title) {
        return title == null ||
               title.trim().isEmpty();
    }

    private boolean gameTitleExist(String title) {
        return getGameFacade().findByQuery(title).stream().anyMatch(game -> game.getTitle().equalsIgnoreCase(title));
    }

    @Override
    public void validate(Object target, Errors errors) {
        GameData game = (GameData) target;
        if (gameTitleIsNullOrEmpty(game.getTitle())) {
            errors.rejectValue("title", "EMPTY_GAME", "EL juego no puede estar vacio");
        } else if (gameTitleExist(game.getTitle())){
            errors.rejectValue("title", "EXIST_GAME", "EL juego ya existe");
        }
    }
}
