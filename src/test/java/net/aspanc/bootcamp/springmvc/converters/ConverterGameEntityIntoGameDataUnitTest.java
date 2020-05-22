package net.aspanc.bootcamp.springmvc.converters;


import net.aspanc.bootcamp.springmvc.constants.GameConstants;
import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.entities.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConverterGameEntityIntoGameDataUnitTest {


    private final Game game = new Game();
    private final ConverterGameEntityIntoGameData converter = new ConverterGameEntityIntoGameData();

    @Before
    public void setUp() {
        game.setId(GameConstants.ID)
                .setTitle(GameConstants.TITLE2)
                .setDescription(GameConstants.DESCRIPTION2)
                .setSteamId(GameConstants.STEAM_ID2);
    }

    @Test
    public void convertGameEntityIntoGameData() {
        final GameData convertedGame = converter.convert(game);
        Assert.assertEquals("Id isn't equal", GameConstants.ID, convertedGame.getId());
        Assert.assertEquals("Title isn't equal", GameConstants.TITLE2, convertedGame.getTitle());
        Assert.assertEquals("Description isn't equal", GameConstants.DESCRIPTION2, convertedGame.getDescription());
        Assert.assertEquals("SteamId isn't equal", GameConstants.STEAM_ID2, convertedGame.getSteamId());
    }

}
