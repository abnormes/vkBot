package ru.onbattle.vkBot.core.commands.impl;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.flows.FlowFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.vk.VKManager;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class Main extends CommandWithButton {

    public Main(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        VKManager.sendKeyboard("Главное меню",
                message.getPeerId(),
                FlowFactory.getKeyboard(FlowFactory.getMainFlow()));

        User.getGuestById(message.getFromId()).setState(State.MAIN);
    }
}
