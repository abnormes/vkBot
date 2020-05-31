package ru.onbattle.vkBot.core;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.commands.impl.Unknown;
import ru.onbattle.vkBot.core.commands.impl.profile.register.RegisterName;
import ru.onbattle.vkBot.core.commands.impl.profile.register.RegisterName2;
import ru.onbattle.vkBot.core.commands.impl.profile.register.RegisterUniversity;
import ru.onbattle.vkBot.dao.domain.User;

import java.util.Collection;

public class CommandDeterminant {

    public static Command getCommand(Collection<Command> commands, Message message) {
        String body = message.getText();
        User user = User.getGuestById(message.getFromId());
        State userState = user.getState();

        if (userState.equals(State.REGISTER)) {
            return new RegisterName("register_name", State.REGISTER);
        } else if (userState.equals(State.REGISTER_NAME)) {
            return new RegisterUniversity("register_university", State.REGISTER);
        } else if (userState.equals(State.REGISTER_2)) {
            return new RegisterName2("register_name_2", State.REGISTER);
        }

        for (Command command : commands) {
            if (command.getName().equals(body)
                    && (command.getState().equals(userState) || command.getState().equals(State.ALL))) {
                return command;
            }
        }

        return new Unknown("unknown command", State.ALL);
    }
}
