package net.aspanc.bootcamp.springmvc.services;

import net.aspanc.bootcamp.springmvc.constants.CredentialsConstants;
import net.aspanc.bootcamp.springmvc.entities.Credentials;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultCredentialsServiceIntegrationTest {
    @Autowired
    @Qualifier("defaultCredentialsService")
    private CredentialsService credentialsService;

    private final Credentials USER1 = new Credentials()
            .setUsername(CredentialsConstants.USERNAME1)
            .setPassword(CredentialsConstants.PASSWORD1)
            .setRole(CredentialsConstants.ROLE)
            .setEnabled(true);;

    private final Credentials USER2 = new Credentials()
            .setUsername(CredentialsConstants.USERNAME2)
            .setPassword(CredentialsConstants.PASSWORD2)
            .setRole(CredentialsConstants.ROLE)
            .setEnabled(true);

    @Before
    public void setUp() {
        if (USER1.getId() == null && USER2.getId() == null) {
            USER1.setId(credentialsService.save(USER1).getId());
            USER2.setId(credentialsService.save(USER2).getId());
        } else {
            credentialsService.save(USER1);
            credentialsService.save(USER2);
        }
    }

    @After
    public void setDown() {
        credentialsService.removeAll();
    }

    @Test
    public void create() {
        final Credentials user = credentialsService.save(new Credentials());
        Assert.assertTrue("Credentials are not in DataBase", credentialsService.existById(user.getId()));
    }

    @Test
    public void retrieveByID() {
        credentialsService.findOne(USER1.getId())
                .ifPresentOrElse(user -> {
                    Assert.assertEquals("Username isn't equal", USER1.getUsername(), user.getUsername());
                    Assert.assertEquals("Password isn't equal", USER1.getPassword(), user.getPassword());
                    Assert.assertEquals("Role isn't equal", USER1.getRole(), user.getRole());
                    Assert.assertTrue("Enability isn't match", USER1.getEnabled() == user.getEnabled());
                }, () -> {
                    Assert.fail();
                });
    }

    @Test
    public void retrieveByUsername() {
        List<Credentials> filterList = credentialsService.findByQuery(CredentialsConstants.TEST_FILTER);
        filterList.forEach(user -> Assert.assertTrue("Username doesn't contains filter", user.getUsername().contains(CredentialsConstants.TEST_FILTER)));
        Assert.assertEquals(1, filterList.size());
    }

    @Test
    public void retrieveAll() {
        Assert.assertEquals("List size doesn't match",3, credentialsService.findAll().size());
    }

    @Test
    public void update() {
        final String NEWDPASSWORD = "alele";
        Assert.assertEquals("Description doesn't match", CredentialsConstants.PASSWORD2, USER2.getPassword());
        USER2.setPassword(NEWDPASSWORD);
        credentialsService.save(USER2);
        credentialsService.findOne(USER2.getId())
                .ifPresentOrElse(user -> {
                    Assert.assertEquals("Changes are not in DataBase", NEWDPASSWORD, user.getPassword());
                }, () -> {
                    Assert.fail();
                });
    }

    @Test
    public void remove() {
        credentialsService.remove(USER1.getId());
        Assert.assertFalse("Game with that ID still in DataBase", credentialsService.existById(USER1.getId()));
    }
}
