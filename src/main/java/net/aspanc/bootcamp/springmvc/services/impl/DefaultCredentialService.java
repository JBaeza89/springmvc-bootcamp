package net.aspanc.bootcamp.springmvc.services.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import net.aspanc.bootcamp.springmvc.daos.CredentialsDao;
import net.aspanc.bootcamp.springmvc.entities.Credentials;
import net.aspanc.bootcamp.springmvc.services.CredentialsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter(AccessLevel.PROTECTED)
@Service("defaultCredentialsService")
public class DefaultCredentialService implements CredentialsService {

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
}
