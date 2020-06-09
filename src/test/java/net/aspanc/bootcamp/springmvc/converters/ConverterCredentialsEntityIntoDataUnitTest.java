package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.constants.CredentialsConstants;
import net.aspanc.bootcamp.springmvc.data.CredentialsData;
import net.aspanc.bootcamp.springmvc.entities.Credentials;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConverterCredentialsEntityIntoDataUnitTest {

    private final Credentials credentials = new Credentials();
    private final ConverterCredentialsEntityIntoData converter = new ConverterCredentialsEntityIntoData();

    @Before
    public void setUp() {
        credentials.setId(CredentialsConstants.ID)
                .setUsername(CredentialsConstants.USERNAME1)
                .setPassword(CredentialsConstants.PASSWORD1)
                .setRole(CredentialsConstants.ROLE)
                .setEnabled(true);
    }

    @Test
    public void converterCredentialsEntityIntoData() {
        final CredentialsData data = converter.convert(credentials);
        Assert.assertEquals("Id isn't equal", credentials.getId(), data.getId());
        Assert.assertEquals("Username isn't equal", credentials.getUsername(), data.getUsername());
        Assert.assertEquals("Password isn't equal", credentials.getPassword(), data.getPassword());
    }
}
