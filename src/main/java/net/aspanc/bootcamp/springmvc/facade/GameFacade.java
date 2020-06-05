package net.aspanc.bootcamp.springmvc.facade;

import com.ibasco.agql.protocols.valve.steam.webapi.pojos.SteamNewsItem;
import com.ibasco.agql.protocols.valve.steam.webapi.pojos.StoreAppDetails;
import lombok.NonNull;
import net.aspanc.bootcamp.springmvc.data.GameData;

import java.util.List;

public interface GameFacade {

    List<GameData> findAll();
    GameData findOne(Long id);
    boolean existsByTitle(String title);
    List<GameData> findByQuery(String filter);
    void remove(Long id);
    GameData save(GameData inputGame);
    StoreAppDetails getGameDetailsBySteamID(Integer steamId);
    List<SteamNewsItem>  getGameNewsBySteamID(@NonNull final Integer steamId);
}
