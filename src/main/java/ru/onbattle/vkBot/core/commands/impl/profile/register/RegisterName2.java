package ru.onbattle.vkBot.core.commands.impl.profile.register;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.Command;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.flows.FlowFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.vk.VKManager;

/**
 * @author abnormes on 31.05.2020
 * @project vkBot
 */
public class RegisterName2 extends Command {

    public RegisterName2(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        String name = message.getText();

        User user = User.getGuestById(message.getFromId());
        user.setName(name);

        VKManager.sendKeyboard("Приятно познакомиться, " + name + "\nИз какого ты ВУЗА?",
                message.getPeerId(), FlowFactory.getKeyboard(FlowFactory.getUniversityFlow()));

        User.getGuestById(message.getFromId()).setState(State.REGISTER_NAME);
    }
}
