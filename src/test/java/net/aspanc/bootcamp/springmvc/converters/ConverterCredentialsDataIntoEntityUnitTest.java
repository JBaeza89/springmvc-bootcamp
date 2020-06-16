package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.constants.CredentialsConstants;
import net.aspanc.bootcamp.springmvc.data.CredentialsData;
import net.aspanc.bootcamp.springmvc.entities.Credentials;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ConverterCredentialsDataIntoEntityUnitTest {

    private final CredentialsData data = new CredentialsData();
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    private final ConverterCredentialsDataIntoEntity converter = new ConverterCredentialsDataIntoEntity(encoder);

    @Before
    public void setUp() {
        data.setId(CredentialsConstants.ID)
                .setUsername(CredentialsConstants.USERNAME1)
                .setPassword(CredentialsConstants.PASSWORD1);
    }

    @Test
    public void converterCredentialsEntityIntoData() {
        final Credentials credentials = converter.convert(data);
        Assert.assertEquals("Id isn't equal", data.getId(), credentials.getId());
        Assert.assertEquals("Username isn't equal", data.getUsername(), credentials.getUsername());
        Assert.assertTrue("Password isn't equal", encoder.matches(data.getPassword(), credentials.getPassword()));
        Assert.assertEquals("Defect role isn't fine", CredentialsConstants.ROLE, credentials.getRole());
        Assert.assertTrue("Defect enable isn't fine", credentials.getEnabled());
    }
}
