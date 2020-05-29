package ru.onbattle.vkBot.core.commands.impl.profile;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.CommandState;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.keyboards.KeyboardFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.vk.VKManager;

/**
 * @author abnormes on 27.05.2020
 * @project vkBot
 */
public class Register extends CommandWithButton {

    public Register(String name, CommandState commandState) {
        super(name, commandState);
    }

    @Override
    public void exec(Message message) {

        VKManager.sendKeyboard("Регистрация, введите ваше имя: ",
                message.getPeerId(),
                KeyboardFactory.getUniversityKeyboard());

        User.getGuestById(message.getFromId()).setCommandState(CommandState.REGISTER);
    }
}