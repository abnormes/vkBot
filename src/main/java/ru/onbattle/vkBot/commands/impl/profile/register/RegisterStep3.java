package ru.onbattle.vkBot.commands.impl.profile.register;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.Command;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.core.VKManager;
import ru.onbattle.vkBot.dao.domain.Role;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.dao.service.UserService;
import ru.onbattle.vkBot.layer.LayerFactory;

import java.util.Collections;

/**
 * @author abnormes on 09.06.2020
 * @project vkBot
 */
public class RegisterStep3 extends Command {

    public RegisterStep3(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {

        if (message.getText().equals("Да")) {
            String msg = "Желаешь ли ты учавствовать в программе развития " +
                    "киберспортсменов Академии Киберспорта OnBattle?";

            VKManager.sendKeyboard(msg,
                    message.getPeerId(), LayerFactory.getKeyboard(LayerFactory.getUniversityLayer()));

            User.getGuestById(message.getFromId()).setState(State.REGISTER_4);
        } else if (message.getText().equals("Нет")) {
            User user = User.getGuestById(message.getFromId());

            if (user.getName() != null) {
                UserService userService = new UserService();

                user.setRating(1400);
                user.setRoles(Collections.singleton(Role.USER));
                user.setActive(true);

                if (user.isUser()) {
                    userService.update(user);
                } else {
                    userService.save(user);
                }

                VKManager.sendKeyboard("Тогда, приветсвуем вас.",
                        message.getPeerId(),
                        LayerFactory.getKeyboard(LayerFactory.getRegisteredLayer()));

                user.setState(State.PROFILE_REGISTERED);
                user.setUser(true);
            }
        }
    }
}
