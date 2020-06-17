package net.aspanc.bootcamp.springmvc.e2etest;

import com.codeborne.selenide.Configuration;
import net.aspanc.bootcamp.springmvc.SpringmvcApplication;
import net.aspanc.bootcamp.springmvc.categories.End2EndTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static com.codeborne.selenide.Selenide.*;


@Category(End2EndTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndToEndTests {

    @LocalServerPort
    private String serverPort;

    @BeforeClass
    public static void initSetUp() {
        Configuration.browser = "firefox";
    }

    @Before
    public void setUp() {
        Configuration.baseUrl= "http://localhost:" + serverPort;
    }
    @Test
    public void checkElementInBaseUrl() {
        open("/");
        $("#login").exists();
    }
    @Test
    public void makeLoginAndCheckLogoutElement() {
        open("/");
        $("#login").click();
        $("#username").setValue("admin");
        $("#password").setValue("nimda");
        $(".btn").click();
        $("#logout").exists();

    }
}
