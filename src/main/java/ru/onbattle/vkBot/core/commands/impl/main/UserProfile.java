package ru.onbattle.vkBot.core.commands.impl.main;

import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.keyboards.impl.ProfileFlow;
import ru.onbattle.vkBot.core.keyboards.impl.UserFlow;
import ru.onbattle.vkBot.dao.domain.Guest;
import ru.onbattle.vkBot.vk.VKManager;

import java.util.List;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class UserProfile extends CommandWithButton {

    public UserProfile(String name, List<KeyboardButton> button, State state) { super(name, state, button); }

    @Override
    public void exec(Message message)  {

        if (Guest.getGuests().get(message.getFromId()).isUser()) {
            VKManager.sendKeyboard("Ваш профиль",
                    message.getPeerId(),
                    new Keyboard().setButtons(ProfileFlow.getButtons()));

            Guest.getGuestById(message.getFromId()).setState(State.PROFILE_REGISTRED);
        } else {
            VKManager.sendKeyboard("Вы не зарегистрированы",
                    message.getPeerId(),
                    new Keyboard().setButtons(UserFlow.getButtons()));

            Guest.getGuestById(message.getFromId()).setState(State.PROFILE_UNREGISTRED);
        }
    }
}
