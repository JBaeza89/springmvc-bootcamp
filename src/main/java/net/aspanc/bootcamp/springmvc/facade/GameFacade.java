package net.aspanc.bootcamp.springmvc.facade;

import lombok.NonNull;
import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.data.ScreenshotData;
import net.aspanc.bootcamp.springmvc.data.SteamNewsData;

import java.util.List;

public interface GameFacade {

    List<GameData> findAll();
    GameData findOne(Long id);
    boolean existsByTitle(String title);
    List<GameData> findByQuery(String filter);
    void remove(Long id);
    GameData save(GameData inputGame);
    ScreenshotData getGameDetailsBySteamID(Integer steamId);
    List<SteamNewsData> getGameNewsBySteamID(Integer steamId);
}
