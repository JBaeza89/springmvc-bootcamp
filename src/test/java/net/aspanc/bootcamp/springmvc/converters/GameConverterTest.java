package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.entities.Game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameConverterTest {

    private final Long ID = (long)2;
    private final String TITLE = "Fifa 20";
    private final String DESCRIPTION = "futbol";
    private final String STEAM_ID = "f2020";

    private final Game gameEntity = new Game().setId(ID).setTitle(TITLE).setDescription(DESCRIPTION).setSteamId(STEAM_ID);
    private final GameData gameData = new GameData().setId(ID).setTitle(TITLE).setDescription(DESCRIPTION).setSteamId(STEAM_ID);
    private final ConverterGameEntityIntoGameData converterEntity = new ConverterGameEntityIntoGameData();
    private final ConverterGameDataIntoGameEntity converterData = new ConverterGameDataIntoGameEntity();

    @Test
    public void convertGameEntityIntoGameData() {
        GameData convertedGame = converterEntity.convert(gameEntity);
        Assert.assertEquals("GameData converted isn't equal", gameData, convertedGame);
    }

    @Test
    public void convertGameDataIntoGameEntity() {
        Game convertedGame = converterData.convert(gameData);
        Assert.assertEquals("Game converted isn't equal", gameEntity, convertedGame);
    }
}
