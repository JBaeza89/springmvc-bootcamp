package net.aspanc.bootcamp.springmvc.facade;

import net.aspanc.bootcamp.springmvc.data.CredentialsData;

import java.util.List;

public interface CredentialsFacade {

    List<CredentialsData> findAll();
    CredentialsData findOne(Long id);
    List<CredentialsData> findByQuery(String filter);
    void remove(Long id);
    CredentialsData save(CredentialsData inputCredentials);
}
