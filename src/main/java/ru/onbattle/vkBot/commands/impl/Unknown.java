package ru.onbattle.vkBot.commands.impl;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.Command;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.flows.LayerFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.core.VKManager;

public class Unknown extends Command {

    public Unknown(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        User user = User.getGuestById(message.getFromId());
        VKManager.sendKeyboard("Неизвестная команда", message.getPeerId(),
                LayerFactory.getKeyboard(LayerFactory.getMainLayer()));
        user.setState(State.MAIN);
    }
}
