package net.aspanc.bootcamp.springmvc.daos;

import net.aspanc.bootcamp.springmvc.entities.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CredentialsDao extends JpaRepository<Credentials, Long> {

    List<Credentials> findByUsernameContaining(String filter);

    Boolean existsByUsername(String username);

    Credentials findByUsername(String username);
}
