package ru.onbattle.vkBot.commands;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.flows.LayerDeterminant;

import java.util.Collection;


public class CommandDeterminant {

    /**
     * @param commands Collection with all commands
     * @param message Message from user
     * @return Command from collection or command from subflows
     */
    public static Command getCommand(Collection<Command> commands, Message message) {
        String body = message.getText();
        User user = User.getGuestById(message.getFromId());
        State userState = user.getState();

        // Logic from subflows
        Command result = LayerDeterminant.detectSubLayer(userState);

        if (result.getName().equals("unknown command")) {
            for (Command command : commands) {
                if (command.getName().equals(body)
                        && (command.getState().equals(userState) || command.getState().equals(State.ALL))) {
                    return command;
                }
            }
        }

        return result;
    }
}
