package ru.onbattle.vkBot.core.commands.impl.profile.register;

import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import ru.onbattle.vkBot.core.Command;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.flows.FlowFactory;
import ru.onbattle.vkBot.dao.domain.User;
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

        if (message.getText().equals("Да")) {
            UserXtrCounters userInfo = VKManager.getUserInfo(message.getFromId());
            String name = userInfo.getFirstName() + " " + userInfo.getLastName();

            User user = User.getGuestById(message.getFromId());
            user.setName(name);

            VKManager.sendKeyboard("Приятно познакомиться, " + name + "\nИз какого ты ВУЗА?",
                    message.getPeerId(), FlowFactory.getKeyboard(FlowFactory.getUniversityFlow()));

            User.getGuestById(message.getFromId()).setState(State.REGISTER_NAME);
        } else if (message.getText().equals("Хочу другое имя")) {
            VKManager.sendMessage("И как вас называть?", message.getPeerId());

            User.getGuestById(message.getFromId()).setState(State.REGISTER_2);
        }
    }
}
