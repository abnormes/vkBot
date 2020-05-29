package ru.onbattle.vkBot.core.commands.impl.main;

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
public class UserProfile extends CommandWithButton {

    public UserProfile(String name, CommandState commandState) { super(name, commandState); }

    @Override
    public void exec(Message message)  {

        if (User.getGuests().get(message.getFromId()).isUser()) {
            VKManager.sendKeyboard("Ваш профиль",
                    message.getPeerId(),
                    KeyboardFactory.getRegisteredKeyboard());

            User.getGuestById(message.getFromId()).setCommandState(CommandState.PROFILE_REGISTERED);
        } else {
            VKManager.sendKeyboard("Вы не зарегистрированы",
                    message.getPeerId(),
                    KeyboardFactory.getUnregisteredKeyboard());

            User.getGuestById(message.getFromId()).setCommandState(CommandState.PROFILE_UNREGISTERED);
        }
    }
}
