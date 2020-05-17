package net.aspanc.bootcamp.springmvc.services;

import net.aspanc.bootcamp.springmvc.daos.GameDao;
import net.aspanc.bootcamp.springmvc.entities.Game;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameService {

    @Autowired
    private GameDao gameDao;

    public List<Game> findAll() {
        return gameDao.findAll();
    }

    public Optional<Game> findOne(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        return gameDao.findById(id);
    }

    private boolean containsInGameTitle(String title, String filter) {
        return title.toLowerCase().contains(filter.toLowerCase());
    }

    public List<Game> findByQuery(String filter) {
        if (filter == null) {
            throw new IllegalArgumentException("filter cannot be null");
        }
        return gameDao.findAll()
                .stream()
                .filter(game -> containsInGameTitle(game.getTitle(), filter))
                .collect(Collectors.toList());
    }

    public void remove(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        gameDao.deleteById(id);
    }

    public void save(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("game cannot be null");
        }
        gameDao.save(game);
    }
}
