package net.aspanc.bootcamp.springmvc.services.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import net.aspanc.bootcamp.springmvc.daos.GameDao;
import net.aspanc.bootcamp.springmvc.entities.Game;
import net.aspanc.bootcamp.springmvc.services.GameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter(AccessLevel.PROTECTED)
@Service("defaultGameService")
public class DefaultGameService implements GameService {

    private final GameDao gameDao;

    public DefaultGameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public List<Game> findAll() {
        return getGameDao().findAll();
    }

    @Override
    public Optional<Game> findOne(@NonNull final Long id) {
        return getGameDao().findById(id);
    }

    @Override
    public boolean existsByTitle(@NonNull final String title) {
        return getGameDao().existsByTitleIgnoreCase(title);
    }

    @Override
    public List<Game> findByQuery(@NonNull final String filter) {
        return getGameDao().findByTitleContaining(filter);
    }

    @Override
    public void remove(@NonNull final Long id) {
        getGameDao().deleteById(id);
    }

    @Override
    public void removeAll() {
        getGameDao().deleteAll();
    }

    @Override
    public Game save(@NonNull final Game game) {
        return getGameDao().save(game);
    }

    @Override
    public boolean existById(final Long id) {
        return getGameDao().existsById(id);
    }

}
