package net.aspanc.bootcamp.springmvc.facade.impl;

import com.ibasco.agql.core.exceptions.BadRequestException;
import com.ibasco.agql.protocols.valve.steam.webapi.interfaces.SteamNews;
import com.ibasco.agql.protocols.valve.steam.webapi.interfaces.SteamStorefront;
import com.ibasco.agql.protocols.valve.steam.webapi.pojos.SteamNewsItem;
import com.ibasco.agql.protocols.valve.steam.webapi.pojos.StoreAppScreenshots;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import net.aspanc.bootcamp.springmvc.data.GameData;
import net.aspanc.bootcamp.springmvc.data.ScreenshotData;
import net.aspanc.bootcamp.springmvc.data.SteamNewsData;
import net.aspanc.bootcamp.springmvc.entities.Game;
import net.aspanc.bootcamp.springmvc.facade.GameFacade;
import net.aspanc.bootcamp.springmvc.services.GameService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Getter(AccessLevel.PROTECTED)
@Component("defaultGameFacade")
public class DefaultGameFacade implements GameFacade {

    private GameService gameService;

    private Converter<GameData, Game> converterGameDataIntoGameEntity;

    private Converter<Game, GameData> converterGameEntityIntoGameData;

    private Converter<SteamNewsItem, SteamNewsData> converterSteamNewsData;

    private Converter<StoreAppScreenshots, ScreenshotData> converterScreenshot;

    private SteamStorefront steamStorefront;

    private SteamNews steamNews;

    public DefaultGameFacade(GameService gameService, Converter<GameData, Game> converterGameDataIntoGameEntity,
                             Converter<Game, GameData> converterGameEntityIntoGameData,
                             Converter<SteamNewsItem, SteamNewsData> converterSteamNewsData,
                             Converter<StoreAppScreenshots, ScreenshotData> converterScreenshot,
                             SteamStorefront steamStorefront, SteamNews steamNews) {

        this.gameService = gameService;
        this.converterGameDataIntoGameEntity = converterGameDataIntoGameEntity;
        this.converterGameEntityIntoGameData = converterGameEntityIntoGameData;
        this.converterSteamNewsData = converterSteamNewsData;
        this.converterScreenshot = converterScreenshot;
        this.steamStorefront = steamStorefront;
        this.steamNews = steamNews;
    }

    @Override
    public List<GameData> findAll() {
        return getGameService().findAll()
                .stream()
                .map(game -> getConverterGameEntityIntoGameData().convert(game))
                .collect(Collectors.toList());
    }

    @Override
    public GameData findOne(@NonNull final Long id) {
        return getConverterGameEntityIntoGameData().convert(getGameService().findOne(id)
                .orElseThrow());
    }

    @Override
    public boolean existsByTitle(@NonNull final String title) {
        return getGameService().existsByTitle(title);
    }

    @Override
    public List<GameData> findByQuery(@NonNull final String filter) {
        return getGameService().findByQuery(filter)
                .stream()
                .map(game -> getConverterGameEntityIntoGameData().convert(game))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(@NonNull final Long id) {
        getGameService().remove(id);
    }

    @Override
    public GameData save(@NonNull final GameData inputGame) {
        final Game entityGame = getConverterGameDataIntoGameEntity().convert(inputGame);
        return getConverterGameEntityIntoGameData().convert(getGameService().save(entityGame));
    }

    @Override
    public ScreenshotData getGameDetailsBySteamID(@NonNull final Integer steamId) {

        List<StoreAppScreenshots> screenshots = getSteamStorefront()
                .getAppDetails(steamId)
                .join()
                .getScreenshots();
        if (screenshots == null || screenshots.isEmpty()) {
            return new ScreenshotData().setUrl("https://image.shutterstock.com/z/stock-vector-no-image-available-sign-absence-of-image-373243873.jpg");
        }
        return converterScreenshot.convert(screenshots
                .get((int) (Math.random() * screenshots.size())));
    }

    @Override
    public List<SteamNewsData> getGameNewsBySteamID(@NonNull final Integer steamId) {
        return getSteamNews().getNewsForApp(steamId, 500, -1, 5, "")
                .join()
                .stream()
                .map(item -> getConverterSteamNewsData().convert(item))
                .collect(Collectors.toList());
    }
}
