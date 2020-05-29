package ru.onbattle.vkBot.core;

import com.vk.api.sdk.objects.messages.KeyboardButtonActionType;
import com.vk.api.sdk.objects.messages.KeyboardButtonColor;
import ru.onbattle.vkBot.core.commands.impl.main.EventList;
import ru.onbattle.vkBot.core.commands.impl.main.Hello;
import ru.onbattle.vkBot.core.commands.impl.Main;
import ru.onbattle.vkBot.core.commands.impl.main.UserProfile;
import ru.onbattle.vkBot.core.commands.impl.profile.Register;
import ru.onbattle.vkBot.core.keyboards.impl.MainFlow;
import ru.onbattle.vkBot.core.keyboards.impl.ProfileFlow;
import ru.onbattle.vkBot.core.keyboards.impl.UserFlow;
import ru.onbattle.vkBot.util.AddButton;

import java.util.HashSet;
import java.util.List;

public class CommandManager {
    private static HashSet<Command> commands = new HashSet<>();

    static {
        Hello hello = new Hello("Привет", AddButton.addButton("Привет",
                KeyboardButtonActionType.TEXT, KeyboardButtonColor.POSITIVE), State.MAIN);
        UserProfile userProfile = new UserProfile("Профиль", AddButton.addButton("Профиль",
                KeyboardButtonActionType.TEXT, KeyboardButtonColor.NEGATIVE), State.MAIN);
        EventList eventList = new EventList("Список турниров", AddButton.addButton("Список турниров",
                KeyboardButtonActionType.TEXT, KeyboardButtonColor.POSITIVE), State.MAIN);
        Main main = new Main("Главное меню", AddButton.addButton("Главное меню",
                KeyboardButtonActionType.TEXT, KeyboardButtonColor.DEFAULT), State.ALL);
        Register register = new Register("Регистрация", AddButton.addButton("Регистрация",
                KeyboardButtonActionType.TEXT, KeyboardButtonColor.NEGATIVE), State.PROFILE_UNREGISTRED);

        commands.add(userProfile);
        commands.add(eventList);
        commands.add(hello);
        commands.add(main);
        commands.add(register);

        MainFlow.addToFlowMany(List.of(userProfile.getButton(), eventList.getButton(), hello.getButton()));
        UserFlow.addToFlowMany(List.of(register.getButton(), main.getButton()));
        ProfileFlow.addToFlowMany(List.of(main.getButton()));
    }

    public static HashSet<Command> getCommands(){
        return commands;
    }
}
