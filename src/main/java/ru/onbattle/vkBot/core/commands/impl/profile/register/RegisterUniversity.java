package ru.onbattle.vkBot.core.commands.impl.profile.register;

import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.Command;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.keyboards.impl.ProfileFlow;
import ru.onbattle.vkBot.core.keyboards.impl.UserFlow;
import ru.onbattle.vkBot.dao.domain.Guest;
import ru.onbattle.vkBot.dao.domain.University;
import ru.onbattle.vkBot.vk.VKManager;

/**
 * @author abnormes on 27.05.2020
 * @project vkBot
 */
public class RegisterUniversity extends Command {

    public RegisterUniversity(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        String universityName = message.getText();
        Guest guest = Guest.getGuestById(message.getFromId());
        University university = new University();
        university.setName(universityName);
        guest.setUniversity(university);
        if(guest.getName() != null) {
            guest.buildUser();
            VKManager.sendKeyboard("Так ты из, " + universityName + "?\nОтлично и добро пожаловать.",
                    message.getPeerId(),
                    new Keyboard().setButtons(ProfileFlow.getButtons()));
        } else {
            VKManager.sendKeyboard("Что-то пошло не так! Попробуй снова!",
                    message.getPeerId(),
                    new Keyboard().setButtons(UserFlow.getButtons()));
        }

        Guest.getGuestById(message.getFromId()).setState(State.PROFILE_REGISTRED);
    }
}