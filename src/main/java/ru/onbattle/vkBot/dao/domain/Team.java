package ru.onbattle.vkBot.dao.domain;

/**
 * @author abnormes on 10.06.2020
 * @project vkBot
 */
public class Team {

    private Integer id;
    private String name;
    private TeamType type;
    private User owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
