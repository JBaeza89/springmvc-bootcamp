package net.aspanc.bootcamp.springmvc.entities;

import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "Games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String description;

    private Integer steamId;

    public Game(){

    }

    public Game(String title, String description, Integer steamId) {
        this.title = title;
        this.description = description;
        this.steamId = steamId;
    }
}
