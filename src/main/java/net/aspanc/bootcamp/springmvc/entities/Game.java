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
@Table(name = "Games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @NotNull
    @Column
    @Getter @Setter
    private String title;

    @Column
    @Getter @Setter
    private String description;

    @Column
    @Getter @Setter
    private String steamId;

    public Game(){

    }

    public Game(String title, String description, String steamId) {
        this.title = title;
        this.description = description;
        this.steamId = steamId;
    }


}
