package net.aspanc.bootcamp.springmvc.facade.impl;

import lombok.NonNull;
import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.entities.Game;
import net.aspanc.bootcamp.springmvc.facade.GameFacade;
import net.aspanc.bootcamp.springmvc.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultGameFacade implements GameFacade {

    @Autowired
    @Qualifier("defaultGameService")
    private GameService gameService;

    @Autowired
    @Qualifier("converterGameDataIntoGameEntity")
    private Converter<GameData, Game> converterGameDataIntoGameEntity;

    @Autowired
    @Qualifier("converterGameEntityIntoGameData")
    private Converter<Game,GameData> converterGameEntityIntoGameData;

    @Override
    public List<GameData> findAll() {
        return gameService.findAll()
                          .stream()
                          .map(game -> converterGameEntityIntoGameData.convert(game))
                          .collect(Collectors.toList());
    }

    @Override
    public GameData findOne(@NonNull final Long id) {
        return converterGameEntityIntoGameData.convert(gameService.findOne(id)
                                                                  .orElseThrow());
    }

    @Override
    public List<GameData> findByQuery(@NonNull final String filter) {
        return gameService.findByQuery(filter)
                .stream()
                .map(game -> converterGameEntityIntoGameData.convert(game))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(@NonNull final Long id) {
        gameService.remove(id);
    }

    @Override
    public GameData save(@NonNull final GameData inputGame) {
        final Game entityGame = converterGameDataIntoGameEntity.convert(inputGame);
        return converterGameEntityIntoGameData.convert(gameService.save(entityGame));
    }
}
