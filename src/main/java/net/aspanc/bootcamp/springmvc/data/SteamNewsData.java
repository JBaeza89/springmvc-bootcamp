package net.aspanc.bootcamp.springmvc.data;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SteamNewsData {

    private String title;
    private String content;
    private String author;
    private String url;

}
