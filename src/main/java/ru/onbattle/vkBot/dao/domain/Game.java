package ru.onbattle.vkBot.dao.domain;

/**
 * @author abnormes on 19.05.2020
 * @project vkBot
 */
public class Game {
    private Long id;
    private String name;
    private Guest guest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
