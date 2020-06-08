package net.aspanc.bootcamp.springmvc.converters;

import com.ibasco.agql.protocols.valve.steam.webapi.pojos.StoreAppScreenshots;
import net.aspanc.bootcamp.springmvc.data.ScreenshotData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("converterScreenshot")
public class ConverterScreenshot implements Converter<StoreAppScreenshots, ScreenshotData> {
    @Override
    public ScreenshotData convert(StoreAppScreenshots storeAppScreenshots) {
        return new ScreenshotData().setUrl(storeAppScreenshots.getFullPath());
    }
}
