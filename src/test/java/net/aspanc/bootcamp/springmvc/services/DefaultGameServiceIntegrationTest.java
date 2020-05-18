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

    @Autowired
    private GameDao gameDao;

    private Game game1;

    private Game game2;

    @Before
    public void setUp() {
        game1 = gameDao.save(new Game("Fifa 20", "futbol", "f2020"));
        game2 = gameDao.save(new Game("NBA 2k20", "basket", "2k20"));
    }

    @After
    public void setDown() {
        gameDao.deleteAll();
    }

    @Test
    public void create() {
        Game game = gameService.save(new Game("Witcher 3", "Epic fantasy", "GR77"));
        Assert.assertTrue("Optional is Empty", gameDao.findById(game.getId()).isPresent());
        Assert.assertEquals("The game list haven't include the saved game", 3, gameDao.findAll().size());
    }

    @Test
    public void retrieve() {
        gameService.findOne(game1.getId())
                .ifPresent(game -> {
                    Assert.assertEquals("Title isn't equal", game1.getTitle(), game.getTitle());
                    Assert.assertEquals("Description isn't equal", game1.getDescription(), game.getDescription());
                });
        Assert.assertEquals("List size doesn't match",2, gameService.findAll().size());
        Assert.assertEquals("Filter list size doesn't match",1, gameService.findByQuery("Fifa").size());
    }

    @Test
    public void update() {
        game2.setDescription("Basket MVP");
        gameService.save(game2);
        gameDao.findById(game2.getId()).ifPresent(game -> {
            Assert.assertEquals("Changes are not in DataBase", "Basket MVP", game.getDescription());
        });
        Assert.assertEquals(2, gameDao.findAll().size());
    }

    @Test
    public void remove() {
        Assert.assertEquals("List size doesn't match", 2, gameDao.findAll().size());
        gameService.remove(game1.getId());
        Assert.assertEquals("List size doesn't match", 1, gameDao.findAll().size());
        Assert.assertFalse("Game1 still present", gameDao.findById(game1.getId()).isPresent());
    }
}
