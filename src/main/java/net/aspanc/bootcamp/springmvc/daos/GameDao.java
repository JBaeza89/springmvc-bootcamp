package net.aspanc.bootcamp.springmvc.daos;

import net.aspanc.bootcamp.springmvc.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameDao extends JpaRepository<Game, Long> {

    @Query(value = "SELECT id, description, steam_id, title FROM games WHERE title LIKE '%:filter%'",
    nativeQuery = true)
    public List<Game> findAllByQuery(@Param("filter") String filter);
}
