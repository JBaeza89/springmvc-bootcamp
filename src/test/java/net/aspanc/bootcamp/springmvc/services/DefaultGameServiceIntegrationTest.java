package net.aspanc.bootcamp.springmvc.services;

import net.aspanc.bootcamp.springmvc.daos.GameDao;
import net.aspanc.bootcamp.springmvc.entities.Game;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultGameServiceIntegrationTest {

    @Autowired
    private GameService gameService;

    private Game game1;

    private Game game2;

    @Before
    public void setUp() {
        game1 = gameService.save(new Game("Fifa 20", "futbol", "f2020"));
        game2 = gameService.save(new Game("NBA 2k20", "basket", "2k20"));
    }

    @After
    public void setDown() {
        gameService.removeAll();
    }

    @Test
    public void create() {
        final Game game = gameService.save(new Game("Witcher 3", "Epic fantasy", "GR77"));
        Assert.assertTrue("Optional is Empty", gameService.findOne(game.getId()).isPresent());
        Assert.assertEquals("The game list haven't include the saved game", 3, gameService.findAll().size());
    }

    @Test
    public void retrieveByID() {
        gameService.findOne(game1.getId())
                .ifPresentOrElse(game -> {
                    Assert.assertEquals("Title isn't equal", game1.getTitle(), game.getTitle());
                    Assert.assertEquals("Description isn't equal", game1.getDescription(), game.getDescription());
                }, () -> {
                    Assert.fail();
                });
    }

    @Test
    public void retrieveByTitle() {
        Assert.assertEquals("Filter list size doesn't match",1, gameService.findByQuery("Fifa").size());
    }

    @Test
    public void retrieveAll() {
        Assert.assertEquals("List size doesn't match",2, gameService.findAll().size());
    }

    @Test
    public void update() {
        gameService.findOne(game2.getId())
                .ifPresentOrElse(game -> {
                    Assert.assertEquals("Changes are not in DataBase", "basket", game.getDescription());
                }, () -> {
                    Assert.fail();
                });
        game2.setDescription("Basket MVP");
        gameService.save(game2);
        gameService.findOne(game2.getId())
                .ifPresentOrElse(game -> {
            Assert.assertEquals("Changes are not in DataBase", "Basket MVP", game.getDescription());
        }, () -> {
                    Assert.fail();
                });
        Assert.assertEquals(2, gameService.findAll().size());
    }

    @Test
    public void remove() {
        Assert.assertTrue("Game with that Id not found", gameService.existById(game1.getId()));
        gameService.remove(game1.getId());
        Assert.assertFalse("Game with that ID still in DataBase", gameService.existById(game1.getId()));
    }
}
