package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.entities.Game;
import org.springframework.core.convert.converter.Converter;

public class ConverterGameDataIntoGameEntity implements Converter<GameData, Game> {
    @Override
    public Game convert(GameData gameData) {
        return new Game()
                .setId(gameData.getId())
                .setDescription(gameData.getDescription())
                .setTitle(gameData.getTitle())
                .setSteamId(gameData.getSteamId());
    }
}
