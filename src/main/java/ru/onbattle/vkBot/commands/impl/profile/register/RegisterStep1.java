package ru.onbattle.vkBot.commands.impl.profile.register;

import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import ru.onbattle.vkBot.commands.Command;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.flows.LayerFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.core.VKManager;

/**
 * @author abnormes on 27.05.2020
 * @project vkBot
 */
public class RegisterStep1 extends Command {

    public RegisterStep1(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {

        if (message.getText().equals("Да")) {
            UserXtrCounters userInfo = VKManager.getUserInfo(message.getFromId());
            String name = userInfo.getFirstName() + " " + userInfo.getLastName();

            User user = User.getGuestById(message.getFromId());
            user.setName(name);

            VKManager.sendKeyboard("Приятно познакомиться, " + name +
                            "\nЖелаешь ли ты учавствовать в программе развития киберспортсменов Академии Киберспорта OnBattle?",
                    message.getPeerId(), LayerFactory.getKeyboard(LayerFactory.getStudentRegistrationLayer()));

            User.getGuestById(message.getFromId()).setState(State.REGISTER_3);
        } else if (message.getText().equals("Хочу другое имя")) {
            VKManager.sendMessage("И как вас называть?", message.getPeerId());

            User.getGuestById(message.getFromId()).setState(State.REGISTER_2);
        }
    }
}
