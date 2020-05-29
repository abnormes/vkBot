package ru.onbattle.vkBot.core;

import ru.onbattle.vkBot.core.commands.impl.Main;
import ru.onbattle.vkBot.core.commands.impl.main.EventList;
import ru.onbattle.vkBot.core.commands.impl.main.UserProfile;
import ru.onbattle.vkBot.core.commands.impl.profile.Register;
import ru.onbattle.vkBot.core.commands.impl.profile.TaskList;
import ru.onbattle.vkBot.core.keyboards.KeyboardFactory;

import java.util.HashSet;
import java.util.List;

public class CommandManager {
    private static HashSet<Command> commands = new HashSet<>();

    static {
        UserProfile userProfile = new UserProfile("Профиль", CommandState.MAIN);
        EventList eventList = new EventList("Список турниров", CommandState.PROFILE_REGISTERED);
        Main main = new Main("Главное меню", CommandState.ALL);
        Register register = new Register("Регистрация", CommandState.PROFILE_UNREGISTERED);
        TaskList taskList = new TaskList("Список задач", CommandState.PROFILE_REGISTERED);

        commands.add(userProfile);
        commands.add(eventList);
        commands.add(main);
        commands.add(register);
        commands.add(taskList);

        KeyboardFactory.addToMain(List.of(
                userProfile
        ));

        KeyboardFactory.addToUnregistered(List.of(
                register,
                main
        ));

        KeyboardFactory.addToRegisteredProfile(List.of(
                taskList,
                eventList,
                main
        ));
    }

    public static HashSet<Command> getCommands(){
        return commands;
    }
}
