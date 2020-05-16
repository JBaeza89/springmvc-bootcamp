package net.aspanc.bootcamp.springmvc.daos;

import net.aspanc.bootcamp.springmvc.entities.Game;
import org.springframework.data.repository.CrudRepository;

public interface IGameDao extends CrudRepository<Game, Long> {
}
