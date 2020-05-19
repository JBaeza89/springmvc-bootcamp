package net.aspanc.bootcamp.springmvc.services;

import net.aspanc.bootcamp.springmvc.entities.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    List<Game> findAll();

    Optional<Game> findOne(final Long id);

    List<Game> findByQuery(final String filter);

    void remove(final Long id);

    void removeAll();

    Game save(final Game game);

    boolean existById(final Long id);



}
