package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.data.CredentialsData;
import net.aspanc.bootcamp.springmvc.entities.Credentials;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("converterCredentialsEntityIntoData")
public class ConverterCredentialsEntityIntoData implements Converter<Credentials, CredentialsData> {

    @Override
    public CredentialsData convert(Credentials credentials) {
        return new CredentialsData()
                .setId(credentials.getId())
                .setUsername(credentials.getUsername())
                .setPassword(credentials.getPassword());
    }
}
