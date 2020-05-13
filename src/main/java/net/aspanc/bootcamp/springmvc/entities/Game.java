package net.aspanc.bootcamp.springmvc.entities;

import lombok.Setter;
import lombok.Getter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
@Table(name = "Games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String description;

    private String steamId;

    public Game(){

    }

    public Game(String title, String description, String steamId) {
        this.title = title;
        this.description = description;
        this.steamId = steamId;
    }


}
