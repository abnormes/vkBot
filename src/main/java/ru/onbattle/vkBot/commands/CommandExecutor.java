package ru.onbattle.vkBot.commands;

import com.vk.api.sdk.objects.messages.Message;

public class CommandExecutor {

    /**
     * Message handler
     *
     * @param message message from user
     */
    public static void execute(Message message) {

        CommandDeterminant.getCommand(CommandManager.getCommands(), message).exec(message);
    }

}
