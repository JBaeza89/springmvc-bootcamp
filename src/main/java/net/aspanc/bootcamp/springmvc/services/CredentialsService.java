package net.aspanc.bootcamp.springmvc.services;

import net.aspanc.bootcamp.springmvc.entities.Credentials;

import java.util.List;
import java.util.Optional;

public interface CredentialsService {

    List<Credentials> findAll();

    Optional<Credentials> findOne(Long id);

    List<Credentials> findByQuery(String filter);

    void remove(Long id);

    void removeAll();

    Credentials save(Credentials credentials);

    boolean existById(Long id);

}
