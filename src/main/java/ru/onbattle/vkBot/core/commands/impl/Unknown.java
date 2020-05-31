package ru.onbattle.vkBot.core.commands.impl;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.Command;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.flows.FlowFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.vk.VKManager;

public class Unknown extends Command {

    public Unknown(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        User user = User.getGuestById(message.getFromId());
        VKManager.sendKeyboard("Неизвестная команда", message.getPeerId(),
                FlowFactory.getKeyboard(FlowFactory.getMainFlow()));
        user.setState(State.MAIN);
    }
}
