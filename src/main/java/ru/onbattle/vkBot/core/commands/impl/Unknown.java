package ru.onbattle.vkBot.core.commands.impl;

import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.Command;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.keyboards.impl.MainFlow;
import ru.onbattle.vkBot.dao.domain.Guest;
import ru.onbattle.vkBot.vk.VKManager;

public class Unknown extends Command {

    public Unknown(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        Guest guest = Guest.getGuestById(message.getFromId());
        VKManager.sendKeyboard("Неизвестная команда", message.getPeerId(), new Keyboard().setButtons(MainFlow.getButtons()));
        guest.setState(State.MAIN);
    }
}
