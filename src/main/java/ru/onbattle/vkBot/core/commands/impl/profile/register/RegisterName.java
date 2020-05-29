package ru.onbattle.vkBot.core.commands.impl.profile.register;

import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.Command;
import ru.onbattle.vkBot.core.CommandState;
import ru.onbattle.vkBot.core.keyboards.KeyboardFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.dao.domain.University;
import ru.onbattle.vkBot.dao.service.UniversityService;
import ru.onbattle.vkBot.util.SetButton;
import ru.onbattle.vkBot.vk.VKManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author abnormes on 27.05.2020
 * @project vkBot
 */
public class RegisterName extends Command {

    private Collection<University> universities = new ArrayList<>();

    public RegisterName(String name, CommandState commandState) {
        super(name, commandState);
    }

    @Override
    public void exec(Message message) {
        Runnable readingThread = () -> {
            String name = message.getText();
            User user = User.getGuestById(message.getFromId());
            user.setName(name);

            UniversityService universityService = new UniversityService();
            universities = universityService.getAll();
            List<String> uniNames = new ArrayList<>();

            for (University university : universities) {
                uniNames.add(university.getName());
            }

            KeyboardFactory.addButtonsToUniversities(SetButton.setButton(uniNames));

            VKManager.sendKeyboard("Приятно познакомиться, " + name + "\nИз какого ты ВУЗА?",
                    message.getPeerId(), KeyboardFactory.getUniversityKeyboard());

            User.getGuestById(message.getFromId()).setCommandState(CommandState.REGISTER_NAME);
        };

        new Thread(readingThread).start();
    }
}
