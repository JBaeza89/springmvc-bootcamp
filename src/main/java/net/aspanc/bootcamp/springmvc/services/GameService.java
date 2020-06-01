package net.aspanc.bootcamp.springmvc.services;

import net.aspanc.bootcamp.springmvc.entities.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    List<Game> findAll();

    Optional<Game> findOne(Long id);

    boolean existsByTitle(String title);

    List<Game> findByQuery(String filter);

    void remove(Long id);

    void removeAll();

    Game save(Game game);

    boolean existById(Long id);



}
