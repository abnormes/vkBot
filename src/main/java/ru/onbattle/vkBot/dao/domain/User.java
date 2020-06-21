package ru.onbattle.vkBot.dao.domain;

import ru.onbattle.vkBot.commands.State;

import java.util.*;

/**
 * @author abnormes on 27.05.2020
 * @project vkBot
 */
public class User {

    private Integer id;
    private State state;
    private Set<Role> roles;
    private Boolean isUser;
    private String name;
    private Integer rating;
    private University university;
    private HashMap<String, Task> taskList;
    private boolean isActive;
    private Game game;
    private static final Map<Integer, User> guests = new HashMap<>();

    public User() {
        rating = 1400;
    }

    public User(Integer id, State state, Boolean isUser) {
        this.id = id;
        this.state = state;
        this.isUser = isUser;
        this.rating = 1400;
        roles = Collections.singleton(Role.USER);
    }

    public static Map<Integer, User> getGuests() {
        return guests;
    }

    public static User getGuestById(int id) {
        return guests.get(id);
    }

    public Boolean isUser() {
        return isUser;
    }

    public void setUser(Boolean user) {
        isUser = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public HashMap<String, Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(HashMap<String, Task> taskList) {
        this.taskList = taskList;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
