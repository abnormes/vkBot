package ru.onbattle.vkBot.core.commands.impl.profile.register;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.Command;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.dao.domain.Guest;
import ru.onbattle.vkBot.vk.VKManager;

/**
 * @author abnormes on 27.05.2020
 * @project vkBot
 */
public class RegisterName extends Command {

    public RegisterName(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        String name = message.getText();
        Guest guest = Guest.getGuestById(message.getFromId());
        guest.setName(name);
        VKManager.sendMessage("Приятно познакомиться, " + name + "\nИз какого ты ВУЗА?", message.getPeerId());
        Guest.getGuestById(message.getFromId()).setState(State.REGISTER_NAME);
    }
}
