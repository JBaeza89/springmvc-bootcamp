package net.aspanc.bootcamp.springmvc.converters;

import com.ibasco.agql.protocols.valve.steam.webapi.pojos.SteamNewsItem;
import net.aspanc.bootcamp.springmvc.data.SteamNewsData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("converterSteamNewsData")
public class ConverterSteamNewsData implements Converter<SteamNewsItem, SteamNewsData> {

    @Override
    public SteamNewsData convert(SteamNewsItem item) {
        return new SteamNewsData()
                .setAuthor(item.getAuthor())
                .setContent(item.getContents())
                .setTitle(item.getTitle());
    }
}
