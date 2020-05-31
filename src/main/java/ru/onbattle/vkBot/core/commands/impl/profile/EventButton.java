package ru.onbattle.vkBot.core.commands.impl.profile;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.flows.FlowFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.vk.VKManager;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class EventButton extends CommandWithButton {


    public EventButton(String name, State state) {
        super(name, state);
    }


    @Override
    public void exec(Message message) {

        VKManager.sendKeyboard("Список турниров",
                message.getPeerId(),
                FlowFactory.getKeyboard(FlowFactory.getEventFlow()));

        User.getGuestById(message.getFromId()).setState(State.EVENT);
    }
}
