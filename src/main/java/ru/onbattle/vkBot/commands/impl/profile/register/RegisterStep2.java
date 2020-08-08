package ru.onbattle.vkBot.commands.impl.profile.register;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.Command;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.layer.LayerFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.core.VKManager;

/**
 * @author abnormes on 31.05.2020
 * @project vkBot
 */
public class RegisterStep2 extends Command {

    public RegisterStep2(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        String name = message.getText();

        User user = User.getGuestById(message.getFromId());
        user.setName(name);

        VKManager.sendKeyboard("Приятно познакомиться, " + name +
                        "\nЖелаешь ли ты учавствовать в программе развития киберспортсменов Академии Киберспорта OnBattle?",
                message.getPeerId(), LayerFactory.getKeyboard(LayerFactory.getStudentRegistrationLayer()));

        User.getGuestById(message.getFromId()).setState(State.REGISTER_3);
    }
}
