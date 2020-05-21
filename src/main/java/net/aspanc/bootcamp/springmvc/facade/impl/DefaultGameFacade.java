package net.aspanc.bootcamp.springmvc.facade.impl;

import lombok.NonNull;
import net.aspanc.bootcamp.springmvc.converters.ConverterGameDataIntoGameEntity;
import net.aspanc.bootcamp.springmvc.converters.ConverterGameEntityIntoGameData;
import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.entities.Game;
import net.aspanc.bootcamp.springmvc.facade.GameFacade;
import net.aspanc.bootcamp.springmvc.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public class DefaultGameFacade implements GameFacade {

    @Autowired
    @Qualifier("defaultGameService")
    private GameService gameService;

    @Autowired
    private ConverterGameDataIntoGameEntity converterGameDataIntoGameEntity;

    @Autowired
    private ConverterGameEntityIntoGameData converterGameEntityIntoGameData;

    @Override
    public List<GameData> findAll() {
        final List<GameData> gameList = new ArrayList<>();
        gameService.findAll()
                   .forEach(game -> {
                       gameList.add(converterGameEntityIntoGameData.convert(game));
                   });
        return gameList;
    }

    @Override
    public GameData findOne(@NonNull final Long id) {
        return converterGameEntityIntoGameData.convert(gameService.findOne(id)
                                                                  .orElseThrow());
    }

    @Override
    public List<GameData> findByQuery(@NonNull final String filter) {
        final List<GameData> gameList = new ArrayList<>();
        gameService.findByQuery(filter)
                   .forEach(game -> {
                       gameList.add(converterGameEntityIntoGameData.convert(game));
                   });
        return gameList;
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
