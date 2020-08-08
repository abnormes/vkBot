package ru.onbattle.vkBot.commands.impl;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.CommandWithButton;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.core.VKManager;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.layer.LayerFactory;

/**
 * @author abnormes on 10.06.2020
 * @project vkBot
 */
public class ReturnToProfileButton extends CommandWithButton {
    public ReturnToProfileButton(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        VKManager.sendKeyboard("Ваш профиль",
                message.getPeerId(),
                LayerFactory.getKeyboard(LayerFactory.getRegisteredLayer()));

        User.getGuestById(message.getFromId()).setState(State.PROFILE_REGISTERED);
    }
}
