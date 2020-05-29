package ru.onbattle.vkBot.core;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.commands.impl.Unknown;
import ru.onbattle.vkBot.core.commands.impl.profile.register.RegisterName;
import ru.onbattle.vkBot.core.commands.impl.profile.register.RegisterUniversity;
import ru.onbattle.vkBot.dao.domain.Guest;

import java.util.Collection;

public class CommandDeterminant {

    public static Command getCommand(Collection<Command> commands, Message message) {
        String body = message.getText();
        Guest guest = Guest.getGuestById(message.getFromId());
        State userState = guest.getState();

        if (userState.equals(State.REGISTER) && !guest.isUser()) {
            return new RegisterName("register_name", State.REGISTER);
        } else if (userState.equals(State.REGISTER_NAME) && !guest.isUser()) {
            return new RegisterUniversity("register_university", State.REGISTER);
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
