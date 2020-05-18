package net.aspanc.bootcamp.springmvc.services.impl;

import lombok.AccessLevel;
import lombok.Getter;
import net.aspanc.bootcamp.springmvc.daos.GameDao;
import net.aspanc.bootcamp.springmvc.entities.Game;
import net.aspanc.bootcamp.springmvc.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("defaultGameService")
public class DefaultGameService implements GameService {

    @Getter(AccessLevel.PROTECTED)
    private final GameDao gameDao;

    public DefaultGameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public List<Game> findAll() {
        return getGameDao().findAll();
    }

    @Override
    public Optional<Game> findOne(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        return getGameDao().findById(id);
    }

    @Override
    public List<Game> findByQuery(String filter) {
        if (filter == null) {
            throw new IllegalArgumentException("filter cannot be null");
        }
        return getGameDao().findByTitleContaining(filter);
    }

    @Override
    public void remove(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        getGameDao().deleteById(id);
    }

    @Override
    public Game save(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("game cannot be null");
        }
        return getGameDao().save(game);
    }

}
