package net.aspanc.bootcamp.springmvc.facade.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.entities.Game;
import net.aspanc.bootcamp.springmvc.facade.GameFacade;
import net.aspanc.bootcamp.springmvc.services.GameService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Getter(AccessLevel.PROTECTED)
@Component("defaultGameFacade")
public class DefaultGameFacade implements GameFacade {

    private GameService gameService;

    private Converter<GameData, Game> converterGameDataIntoGameEntity;

    private Converter<Game,GameData> converterGameEntityIntoGameData;

    public DefaultGameFacade(GameService gameService, Converter<GameData, Game> converterGameDataIntoGameEntity, Converter<Game, GameData> converterGameEntityIntoGameData) {
        this.gameService = gameService;
        this.converterGameDataIntoGameEntity = converterGameDataIntoGameEntity;
        this.converterGameEntityIntoGameData = converterGameEntityIntoGameData;
    }

    @Override
    public List<GameData> findAll() {
        return getGameService().findAll()
                          .stream()
                          .map(game -> getConverterGameEntityIntoGameData().convert(game))
                          .collect(Collectors.toList());
    }

    @Override
    public GameData findOne(@NonNull final Long id) {
        return getConverterGameEntityIntoGameData().convert(getGameService().findOne(id)
                                                                  .orElseThrow());
    }

    @Override
    public boolean existsByTitle(@NonNull final String title) {
        return getGameService().existsByTitle(title);
    }

    @Override
    public List<GameData> findByQuery(@NonNull final String filter) {
        return getGameService().findByQuery(filter)
                .stream()
                .map(game -> getConverterGameEntityIntoGameData().convert(game))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(@NonNull final Long id) {
        getGameService().remove(id);
    }

    @Override
    public GameData save(@NonNull final GameData inputGame) {
        final Game entityGame = getConverterGameDataIntoGameEntity().convert(inputGame);
        return getConverterGameEntityIntoGameData().convert(getGameService().save(entityGame));
    }
}
