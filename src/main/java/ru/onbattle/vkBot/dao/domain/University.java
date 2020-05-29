package ru.onbattle.vkBot.dao.domain;

import java.util.List;

/**
 * @author abnormes on 17.05.2020
 * @project vkBot
 */
public class University {
    private Long id;
    private String name;
    private List<Guest> users;

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

    public List<Guest> getUsers() {
        return users;
    }

    public void setUsers(List<Guest> users) {
        this.users = users;
    }
}
