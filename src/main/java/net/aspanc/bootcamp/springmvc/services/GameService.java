package net.aspanc.bootcamp.springmvc.services;

import net.aspanc.bootcamp.springmvc.entities.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    public List<Game> findAll();

    public Optional<Game> findOne(final Long id);

    public List<Game> findByQuery(final String filter);

    public void remove(final Long id);

    public Game save(final Game game);

}
