package net.aspanc.bootcamp.springmvc.daos;

import net.aspanc.bootcamp.springmvc.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameDao extends JpaRepository<Game, Long> {

    List<Game> findByTitleContaining(final String filter);
}
