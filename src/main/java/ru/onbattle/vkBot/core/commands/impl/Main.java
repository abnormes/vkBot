package ru.onbattle.vkBot.core.commands.impl;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.CommandState;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.keyboards.KeyboardFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.vk.VKManager;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class Main extends CommandWithButton {

    public Main(String name, CommandState commandState) {
        super(name, commandState);
    }

    @Override
    public void exec(Message message) {
        VKManager.sendKeyboard("Главное меню:",
                message.getPeerId(),
                KeyboardFactory.getMainKeyboard());

        User.getGuestById(message.getFromId()).setCommandState(CommandState.MAIN);
    }
}
