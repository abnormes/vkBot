package ru.onbattle.vkBot.commands.impl;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.commands.CommandWithButton;
import ru.onbattle.vkBot.flows.LayerFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.core.VKManager;

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
                LayerFactory.getKeyboard(LayerFactory.getMainLayer()));

        User.getGuestById(message.getFromId()).setState(State.MAIN);
    }
}
