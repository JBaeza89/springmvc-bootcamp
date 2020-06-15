package net.aspanc.bootcamp.springmvc.services.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import net.aspanc.bootcamp.springmvc.daos.CredentialsDao;
import net.aspanc.bootcamp.springmvc.entities.Credentials;
import net.aspanc.bootcamp.springmvc.services.CredentialsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter(AccessLevel.PROTECTED)
@Service("defaultCredentialsService")
public class DefaultCredentialService implements CredentialsService, UserDetailsService {

    private final CredentialsDao credentialsDao;

    public DefaultCredentialService(CredentialsDao credentialsDao) {
        this.credentialsDao = credentialsDao;
    }

    @Override
    public List<Credentials> findAll() {
        return getCredentialsDao().findAll();
    }

    @Override
    public Optional<Credentials> findOne(@NonNull final Long id) {
        return getCredentialsDao().findById(id);
    }

    @Override
    public List<Credentials> findByQuery(@NonNull final String filter) {
        return getCredentialsDao().findByUsernameContaining(filter);
    }

    @Override
    public void remove(@NonNull final Long id) {
        getCredentialsDao().deleteById(id);
    }

    @Override
    public void removeAll() {
        getCredentialsDao().deleteAll();
    }

    @Override
    public Credentials save(@NonNull final Credentials credentials) {
        return getCredentialsDao().save(credentials);
    }

    @Override
    public boolean existById(@NonNull final Long id) {
        return getCredentialsDao().existsById(id);
    }

    @Override
    public Boolean existByUsername(@NonNull final String username) {
        return getCredentialsDao().existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull final String username) throws UsernameNotFoundException {
        Credentials credentials = credentialsDao.findByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(credentials.getRole()));
        UserDetails userDetails = new User(credentials.getUsername(), credentials.getPassword(), roles);
        return userDetails;
    }
}
