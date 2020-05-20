package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.entities.Game;
import org.springframework.core.convert.converter.Converter;

public class ConverterGameEntityIntoGameData implements Converter<Game, GameData> {


    @Override
    public GameData convert(final Game game) {
        return new GameData()
                .setId(game.getId())
                .setDescription(game.getDescription())
                .setTitle(game.getTitle())
                .setSteamId(game.getSteamId());
    }
}
