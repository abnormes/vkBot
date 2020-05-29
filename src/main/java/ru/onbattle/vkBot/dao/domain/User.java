package ru.onbattle.vkBot.dao.domain;

import ru.onbattle.vkBot.core.CommandState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author abnormes on 27.05.2020
 * @project vkBot
 */
public class User {

    private Integer id;
    private CommandState commandState;
    private Boolean isUser;
    private String name;
    private Integer rating;
    private University university;
    private List<Task> taskList;
    private Game game;
    private static Map<Integer, User> guests = new HashMap<>();

    public User() {}

    public User(Integer id, CommandState commandState, Boolean isUser) {
        this.id = id;
        this.commandState = commandState;
        this.isUser = isUser;
        this.rating = 1400;
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

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CommandState getCommandState() {
        return commandState;
    }

    public void setCommandState(CommandState commandState) {
        this.commandState = commandState;
    }
}
