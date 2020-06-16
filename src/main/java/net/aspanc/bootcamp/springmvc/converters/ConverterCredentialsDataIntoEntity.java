package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.data.CredentialsData;
import net.aspanc.bootcamp.springmvc.entities.Credentials;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component("converterCredentialsDataIntoEntity")
public class ConverterCredentialsDataIntoEntity implements Converter<CredentialsData, Credentials> {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ConverterCredentialsDataIntoEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Credentials convert(CredentialsData credentialsData) {
        return new Credentials()
                .setId(credentialsData.getId())
                .setPassword(bCryptPasswordEncoder.encode(credentialsData.getPassword()))
                .setUsername(credentialsData.getUsername())
                .setRole("REGISTERED")
                .setEnabled(true);
    }
}
