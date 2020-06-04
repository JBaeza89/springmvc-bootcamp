package net.aspanc.bootcamp.springmvc.facade;

import net.aspanc.bootcamp.springmvc.data.GameData;

import java.util.List;

public interface GameFacade extends SteamApiFacade {

    List<GameData> findAll();
    GameData findOne(Long id);
    boolean existsByTitle(String title);
    List<GameData> findByQuery(String filter);
    void remove(Long id);
    GameData save(GameData inputGame);

}
