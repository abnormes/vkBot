package ru.onbattle.vkBot.core.commands.impl.profile;

import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.keyboards.impl.RegisterFlow;
import ru.onbattle.vkBot.dao.domain.Guest;
import ru.onbattle.vkBot.vk.VKManager;

import java.util.List;

/**
 * @author abnormes on 27.05.2020
 * @project vkBot
 */
public class Register extends CommandWithButton {

    public Register(String name, List<KeyboardButton> button, State state) {
        super(name, state, button);
    }

    @Override
    public void exec(Message message) {

        VKManager.sendKeyboard("Регистрация, введите ваше имя: ",
                message.getPeerId(),
                new Keyboard().setButtons(RegisterFlow.getButtons()));

        Guest.getGuestById(message.getFromId()).setState(State.REGISTER);
    }
}