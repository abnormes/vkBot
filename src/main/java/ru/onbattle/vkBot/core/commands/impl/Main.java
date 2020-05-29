package ru.onbattle.vkBot.core.commands.impl;

import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.keyboards.impl.MainFlow;
import ru.onbattle.vkBot.dao.domain.Guest;
import ru.onbattle.vkBot.vk.VKManager;

import java.util.List;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class Main extends CommandWithButton {

    public Main(String name, List<KeyboardButton> button, State state) {
        super(name, state, button);
    }

    @Override
    public void exec(Message message) {
        VKManager.sendKeyboard("Главное меню:",
                message.getPeerId(),
                new Keyboard().setButtons(MainFlow.getButtons()));

        Guest.getGuestById(message.getFromId()).setState(State.MAIN);
    }
}
