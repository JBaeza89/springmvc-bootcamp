package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.constants.GameConstants;
import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.entities.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConverterGameDataIntoGameEntityUnitTest {

    private final GameData gameData = new GameData();
    private final ConverterGameDataIntoGameEntity converter = new ConverterGameDataIntoGameEntity();

    @Before
    public void setUp() {
        gameData.setId(GameConstants.ID)
                .setTitle(GameConstants.TITLE1)
                .setDescription(GameConstants.DESCRIPTION1)
                .setSteamId(GameConstants.STEAM_ID1);
    }

    @Test
    public void convertGameEntityIntoGameData() {
        final Game convertedGame = converter.convert(gameData);
        Assert.assertEquals("Id isn't equal", GameConstants.ID, convertedGame.getId());
        Assert.assertEquals("Title isn't equal", GameConstants.TITLE1, convertedGame.getTitle());
        Assert.assertEquals("Description isn't equal", GameConstants.DESCRIPTION1, convertedGame.getDescription());
        Assert.assertEquals("SteamId isn't equal", GameConstants.STEAM_ID1, convertedGame.getSteamId());
    }
}
