package net.aspanc.bootcamp.springmvc.services;

import net.aspanc.bootcamp.springmvc.entities.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    public List<Game> findAll();

    public Optional<Game> findOne(Long id);

    public List<Game> findByQuery(String filter);

    public void remove(Long id);

    public Game save(Game game);

}
