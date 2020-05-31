package ru.onbattle.vkBot.core.commands.impl.main;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.commands.impl.profile.EditProfile;
import ru.onbattle.vkBot.core.flows.FlowFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.vk.VKManager;

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
                    FlowFactory.getKeyboard(FlowFactory.getRegisteredFlow()));

            User.getGuestById(message.getFromId()).setState(State.PROFILE_REGISTERED);
        } else {
            VKManager.sendKeyboard("Вы не зарегистрированы",
                    message.getPeerId(),
                    FlowFactory.getKeyboard(FlowFactory.getUnregisteredFlow()));

            User.getGuestById(message.getFromId()).setState(State.PROFILE_UNREGISTERED);
        }
    }
}
