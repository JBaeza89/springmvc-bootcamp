package net.aspanc.bootcamp.springmvc.services;

import net.aspanc.bootcamp.springmvc.entities.Game;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultGameServiceIntegrationTest {

    @Autowired
    private GameService gameService;

    private final String TITLE1 = "Fifa 20";
    private final String DESCRIPTION1 = "futbol";
    private final String STEAM_ID1 = "f2020";
    private final String TITLE2 = "NBA 2k20";
    private final String DESCRIPTION2 = "basket";
    private final String STEAM_ID2 = "2k20";

    private final Game GAME1 = new Game(TITLE1, DESCRIPTION1, STEAM_ID1);

    private final Game GAME2 = new Game(TITLE2, DESCRIPTION2, STEAM_ID2);


    @Before
    public void setUp() {
        if (GAME1.getId() == null && GAME2.getId() == null) {
            GAME1.setId(gameService.save(GAME1).getId());
            GAME2.setId(gameService.save(GAME2).getId());
        } else {
            gameService.save(GAME1);
            gameService.save(GAME2);
        }
    }

    @After
    public void setDown() {
        gameService.removeAll();
    }

    @Test
    public void create() {
        final Game game = gameService.save(new Game("Witcher 3", "Epic fantasy", "GR77"));
        Assert.assertTrue("Game are not in DataBase", gameService.existById(game.getId()));
    }

    @Test
    public void retrieveByID() {
        gameService.findOne(GAME1.getId())
                .ifPresentOrElse(game -> {
                    Assert.assertEquals("Title isn't equal", GAME1.getTitle(), game.getTitle());
                    Assert.assertEquals("Description isn't equal", GAME1.getDescription(), game.getDescription());
                }, () -> {
                    Assert.fail();
                });
    }

    @Test
    public void retrieveByTitle() {
        final String FILTER = "Fifa";
        List<Game> filterList = gameService.findByQuery(FILTER);
        filterList.forEach(game -> Assert.assertTrue("Title doesn't contains filter", game.getTitle().contains(FILTER)));
        Assert.assertEquals(1, filterList.size());
    }

    @Test
    public void retrieveAll() {
        Assert.assertEquals("List size doesn't match",2, gameService.findAll().size());
    }

    @Test
    public void update() {
        final String NEWDESCRIPTION = "Basket MVP";
        Assert.assertEquals("Description doesn't match", DESCRIPTION2, GAME2.getDescription());
        GAME2.setDescription(NEWDESCRIPTION);
        gameService.save(GAME2);
        gameService.findOne(GAME2.getId())
                .ifPresentOrElse(game -> {
            Assert.assertEquals("Changes are not in DataBase", NEWDESCRIPTION, game.getDescription());
        }, () -> {
                    Assert.fail();
                });
    }

    @Test
    public void remove() {
        gameService.remove(GAME1.getId());
        Assert.assertFalse("Game with that ID still in DataBase", gameService.existById(GAME1.getId()));
    }
}
