package ru.onbattle.vkBot.core.commands.impl.profile;

import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.flows.FlowFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.vk.VKManager;

/**
 * @author abnormes on 27.05.2020
 * @project vkBot
 */
public class Register extends CommandWithButton {

    public Register(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        register(message);
    }

    static void register(Message message) {
        UserXtrCounters userInfo = VKManager.getUserInfo(message.getFromId());
        String name = userInfo.getFirstName() + " " + userInfo.getLastName();

        VKManager.sendKeyboard("Регистрация. \nВаше имя " + name + "?",
                message.getPeerId(),
                FlowFactory.getKeyboard(FlowFactory.getNameFlow()).setOneTime(true));

        User.getGuestById(message.getFromId()).setState(State.REGISTER);
    }
}