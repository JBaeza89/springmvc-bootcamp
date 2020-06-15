package net.aspanc.bootcamp.springmvc.converters;

import net.aspanc.bootcamp.springmvc.entities.Credentials;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component("converterCredentialsIntoUserDetails")
public class ConverterCredentialsIntoUserDetails implements Converter<Credentials, UserDetails> {
    @Override
    public UserDetails convert(Credentials credentials) {
        return new User(credentials.getUsername(), credentials.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(credentials.getRole())));
    }
}
