package ru.onbattle.vkBot.core.commands.impl;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.Command;
import ru.onbattle.vkBot.core.CommandState;
import ru.onbattle.vkBot.core.keyboards.KeyboardFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.vk.VKManager;

public class Unknown extends Command {

    public Unknown(String name, CommandState commandState) {
        super(name, commandState);
    }

    @Override
    public void exec(Message message) {
        User user = User.getGuestById(message.getFromId());
        VKManager.sendKeyboard("Неизвестная команда", message.getPeerId(), KeyboardFactory.getMainKeyboard());
        user.setCommandState(CommandState.MAIN);
    }
}
