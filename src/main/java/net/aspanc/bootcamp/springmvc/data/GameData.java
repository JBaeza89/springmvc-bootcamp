package net.aspanc.bootcamp.springmvc.data;


import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class GameData {

    private Long id;
    @NotNull
    @NotEmpty
    private String title;
    private String description;
    @Digits(integer = 10, fraction = 0)
    private Integer steamId;

    public GameData setId(final Long id) {
        this.id = id;
        return this;
    }

    public GameData setTitle(final String title) {
        this.title = title;
        return this;
    }

    public GameData setDescription(final String description) {
        this.description = description;
        return this;
    }

    public GameData setSteamId(final Integer steamId) {
        this.steamId = steamId;
        return this;
    }

}
