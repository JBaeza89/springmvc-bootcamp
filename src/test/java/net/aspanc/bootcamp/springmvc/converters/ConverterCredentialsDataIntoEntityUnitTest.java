package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.constants.CredentialsConstants;
import net.aspanc.bootcamp.springmvc.data.CredentialsData;
import net.aspanc.bootcamp.springmvc.entities.Credentials;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConverterCredentialsDataIntoEntityUnitTest {

    private final CredentialsData data = new CredentialsData();
    private final ConverterCredentialsDataIntoEntity converter = new ConverterCredentialsDataIntoEntity();

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
        Assert.assertEquals("Password isn't equal", data.getPassword(), credentials.getPassword());
        Assert.assertEquals("Defect role isn't fine", CredentialsConstants.ROLE, credentials.getRole());
        Assert.assertTrue("Defect enable isn't fine", credentials.getEnabled());
    }
}
