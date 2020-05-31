package ru.onbattle.vkBot.core;

import ru.onbattle.vkBot.core.commands.impl.Main;
import ru.onbattle.vkBot.core.commands.impl.main.About;
import ru.onbattle.vkBot.core.commands.impl.profile.EventButton;
import ru.onbattle.vkBot.core.commands.impl.main.UserProfile;
import ru.onbattle.vkBot.core.commands.impl.profile.EditProfile;
import ru.onbattle.vkBot.core.commands.impl.profile.Register;
import ru.onbattle.vkBot.core.commands.impl.profile.TaskList;
import ru.onbattle.vkBot.core.flows.FlowConfig;
import ru.onbattle.vkBot.core.flows.FlowFactory;

import java.util.HashSet;
import java.util.List;

public class CommandManager {
    private static HashSet<Command> commands = new HashSet<>();
    private static FlowConfig flowConfig = new FlowConfig();

    static {
        UserProfile userProfile = new UserProfile("Мой профиль", State.MAIN);
        About about = new About("О проекте", State.MAIN);
        EventButton eventButton = new EventButton("Список турниров", State.PROFILE_REGISTERED);
        Main main = new Main("Главное меню", State.ALL);
        Register register = new Register("Регистрация", State.PROFILE_UNREGISTERED);
        TaskList taskList = new TaskList("Список задач", State.PROFILE_REGISTERED);
        EditProfile editProfile = new EditProfile("Редактировать профиль", State.PROFILE_REGISTERED);

        commands.add(userProfile);
        commands.add(eventButton);
        commands.add(editProfile);
        commands.add(main);
        commands.add(register);
        commands.add(taskList);
        commands.add(about);

        flowConfig.init(FlowFactory.getMainFlow(), List.of(userProfile, about));
        flowConfig.init(FlowFactory.getEventFlow(), List.of(main));
        flowConfig.init(FlowFactory.getRegisteredFlow(), List.of(taskList, eventButton, editProfile, main));
        flowConfig.init(FlowFactory.getUnregisteredFlow(), List.of(register, main));
    }

    public static HashSet<Command> getCommands() {
        return commands;
    }
}
