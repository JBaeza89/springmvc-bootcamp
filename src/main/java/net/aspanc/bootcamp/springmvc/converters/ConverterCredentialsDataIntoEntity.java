package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.data.CredentialsData;
import net.aspanc.bootcamp.springmvc.entities.Credentials;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("converterCredentialsDataIntoEntity")
public class ConverterCredentialsDataIntoEntity implements Converter<CredentialsData, Credentials> {
    @Override
    public Credentials convert(CredentialsData credentialsData) {
        return new Credentials()
                .setId(credentialsData.getId())
                .setPassword(credentialsData.getPassword())
                .setUsername(credentialsData.getUsername())
                .setRole("REGISTERED")
                .setEnabled(true);
    }
}
