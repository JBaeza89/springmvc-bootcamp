package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.entities.Game;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("converterGameDataIntoGameEntity")
public class ConverterGameDataIntoGameEntity implements Converter<GameData, Game> {
    @Override
    public Game convert(final GameData gameData) {
        return new Game()
                .setId(gameData.getId())
                .setDescription(gameData.getDescription())
                .setTitle(gameData.getTitle())
                .setSteamId(gameData.getSteamId());
    }
}
