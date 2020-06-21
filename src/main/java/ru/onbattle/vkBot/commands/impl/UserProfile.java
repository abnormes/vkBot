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
public class UserProfile extends CommandWithButton {

    public UserProfile(String name, State state) { super(name, state); }

    @Override
    public void exec(Message message)  {

        if (User.getGuests().get(message.getFromId()).isUser()) {
            VKManager.sendKeyboard("Ваш профиль",
                    message.getPeerId(),
                    LayerFactory.getKeyboard(LayerFactory.getRegisteredLayer()));

            User.getGuestById(message.getFromId()).setState(State.PROFILE_REGISTERED);
        } else {
            VKManager.sendKeyboard("Вы не зарегистрированы",
                    message.getPeerId(),
                    LayerFactory.getKeyboard(LayerFactory.getUnregisteredLayer()));

            User.getGuestById(message.getFromId()).setState(State.PROFILE_UNREGISTERED);
        }
    }
}
