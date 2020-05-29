package ru.onbattle.vkBot.core;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.commands.impl.Unknown;
import ru.onbattle.vkBot.core.commands.impl.profile.register.RegisterName;
import ru.onbattle.vkBot.core.commands.impl.profile.register.RegisterUniversity;
import ru.onbattle.vkBot.dao.domain.User;

import java.util.Collection;

public class CommandDeterminant {

    public static Command getCommand(Collection<Command> commands, Message message) {
        String body = message.getText();
        User user = User.getGuestById(message.getFromId());
        CommandState userCommandState = user.getCommandState();

        if (userCommandState.equals(CommandState.REGISTER) && !user.isUser()) {
            return new RegisterName("register_name", CommandState.REGISTER);
        } else if (userCommandState.equals(CommandState.REGISTER_NAME) && !user.isUser()) {
            return new RegisterUniversity("register_university", CommandState.REGISTER);
        }

        for (Command command : commands) {
            if (command.getName().equals(body)
                    && (command.getCommandState().equals(userCommandState) || command.getCommandState().equals(CommandState.ALL))) {
                return command;
            }
        }

        return new Unknown("unknown command", CommandState.ALL);
    }
}
