package ru.onbattle.vkBot.dao.domain;

import java.util.*;

/**
 * @author abnormes on 17.05.2020
 * @project vkBot
 */
public class University {
    private Integer id;
    private String name;
    private List<User> users;
    private static Map<Integer, University> universities = new HashMap();

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

    public List<User> getUsers() {
        return users;
    }

    public static Map getUniversities() {
        return universities;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
