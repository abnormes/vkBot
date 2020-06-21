package ru.onbattle.vkBot.commands;

import ru.onbattle.vkBot.commands.impl.Main;
import ru.onbattle.vkBot.commands.impl.main.About;
import ru.onbattle.vkBot.commands.impl.profile.*;
import ru.onbattle.vkBot.commands.impl.UserProfile;
import ru.onbattle.vkBot.commands.impl.profile.student.TaskButton;
import ru.onbattle.vkBot.flows.LayerConfig;
import ru.onbattle.vkBot.flows.LayerFactory;

import java.util.HashSet;
import java.util.List;

public class CommandManager {

    /**
     * @param commands Commands for first level flow
     * @param flowConfig Config class for flow (keyboard)
     */
    private static final HashSet<Command> COMMANDS = new HashSet<>();
    private static final LayerConfig LAYER_CONFIG = new LayerConfig();

    static {
        // Commands
        Command userProfile = new UserProfile("Ваш профиль", State.ALL);
        Command about = new About("О проекте", State.MAIN);
        Command eventButton = new EventButton("Список турниров", State.PROFILE_REGISTERED);
        Command main = new Main("Главное меню", State.ALL);
        Command register = new Register("Регистрация", State.PROFILE_UNREGISTERED);
        Command taskList = new TaskButton("Список задач", State.STUDENT_PROFILE);
        Command editProfile = new EditProfile("Редактировать профиль", State.PROFILE_REGISTERED);
        Command studentButton = new StudentButton("Меню студента", State.PROFILE_REGISTERED);

        // Adding first level commands to collection
        COMMANDS.add(userProfile);
        COMMANDS.add(eventButton);
        COMMANDS.add(editProfile);
        COMMANDS.add(main);
        COMMANDS.add(register);
        COMMANDS.add(taskList);
        COMMANDS.add(about);
        COMMANDS.add(studentButton);

        // Keyboard configuration
        LAYER_CONFIG.init(LayerFactory.getMainLayer(), List.of(userProfile, about));
        LAYER_CONFIG.init(LayerFactory.getEventLayer(), List.of(userProfile, main));
        LAYER_CONFIG.init(LayerFactory.getRegisteredLayer(), List.of(studentButton, eventButton, editProfile, main));
        LAYER_CONFIG.init(LayerFactory.getUnregisteredLayer(), List.of(register, main));
        LAYER_CONFIG.init(LayerFactory.getStudentLayer(), List.of(taskList, userProfile));
        LAYER_CONFIG.init(LayerFactory.getTaskLayer(), List.of(userProfile));
        LAYER_CONFIG.init(LayerFactory.getTaskInfoLayer(), List.of(userProfile));
    }

    public static HashSet<Command> getCommands() {
        return COMMANDS;
    }
}
