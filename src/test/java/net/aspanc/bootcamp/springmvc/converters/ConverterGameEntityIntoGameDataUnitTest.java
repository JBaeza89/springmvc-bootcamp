package net.aspanc.bootcamp.springmvc.converters;


import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.entities.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConverterGameEntityIntoGameDataUnitTest {

    private final Long ID = 2l;
    private final String TITLE = "Fifa 20";
    private final String DESCRIPTION = "futbol";
    private final String STEAM_ID = "f2020";
    private final Game game = new Game();
    private final ConverterGameEntityIntoGameData converter = new ConverterGameEntityIntoGameData();

    @Before
    public void setUp() {
        game.setId(ID).setTitle(TITLE).setDescription(DESCRIPTION).setSteamId(STEAM_ID);
    }

    @Test
    public void convertGameEntityIntoGameData() {
        final GameData convertedGame = converter.convert(game);
        Assert.assertEquals("Id isn't equal", ID, convertedGame.getId());
        Assert.assertEquals("Title isn't equal", TITLE, convertedGame.getTitle());
        Assert.assertEquals("Description isn't equal", DESCRIPTION, convertedGame.getDescription());
        Assert.assertEquals("SteamId isn't equal", STEAM_ID, convertedGame.getSteamId());
    }

}
