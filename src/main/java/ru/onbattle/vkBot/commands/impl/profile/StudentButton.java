package ru.onbattle.vkBot.commands.impl.profile;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.CommandWithButton;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.core.VKManager;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.layer.LayerFactory;

/**
 * @author abnormes on 10.06.2020
 * @project vkBot
 */
public class StudentButton extends CommandWithButton {

    public StudentButton(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        VKManager.sendKeyboard("Меню студента",
                message.getPeerId(),
                LayerFactory.getKeyboard(LayerFactory.getStudentLayer()));

        User.getGuestById(message.getFromId()).setState(State.STUDENT_PROFILE);
    }
}
