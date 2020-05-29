package ru.onbattle.vkBot.core.commands.impl.profile.register;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.Command;
import ru.onbattle.vkBot.core.CommandState;
import ru.onbattle.vkBot.core.keyboards.KeyboardFactory;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.dao.domain.University;
import ru.onbattle.vkBot.dao.service.UniversityService;
import ru.onbattle.vkBot.dao.service.UserService;
import ru.onbattle.vkBot.vk.VKManager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author abnormes on 27.05.2020
 * @project vkBot
 */
public class RegisterUniversity extends Command {

    private Collection<University> universities = new ArrayList<>();

    public RegisterUniversity(String name, CommandState commandState) {
        super(name, commandState);
    }

    @Override
    public void exec(Message message) {
        Runnable readingThread = () -> {
            String universityName = message.getText();
            universities = new UniversityService().getAll();

            University university = new University();
            boolean isUniversity = false;

            for (University elem : universities) {
                if (universityName.equals(elem.getName())) {
                    university.setId(elem.getId());
                    university.setName(elem.getName());
                    isUniversity = true;
                }
            }
            if (isUniversity) {
                User user = User.getGuestById(message.getFromId());
                user.setUniversity(university);
                if (user.getName() != null) {
                    /** DAO send to DB */
                    UserService userService = new UserService();
                    userService.save(user);
                    user.setUser(true);


                    VKManager.sendKeyboard("Так ты из, " + universityName + "?\nОтлично и добро пожаловать.",
                            message.getPeerId(),
                            KeyboardFactory.getRegisteredKeyboard());
                } else {
                    VKManager.sendKeyboard("Что-то пошло не так! Попробуй снова!",
                            message.getPeerId(),
                            KeyboardFactory.getUnregisteredKeyboard());
                }
                User.getGuestById(message.getFromId()).setCommandState(CommandState.PROFILE_REGISTERED);
            } else {
                VKManager.sendKeyboard("Прости, но я не слышал о таком универе, попробуй снова",
                        message.getPeerId(),
                        KeyboardFactory.getUniversityKeyboard());

                User.getGuestById(message.getFromId()).setCommandState(CommandState.REGISTER_NAME);
            }
        };

        new Thread(readingThread).start();
    }
}