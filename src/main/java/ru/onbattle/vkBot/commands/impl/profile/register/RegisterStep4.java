package ru.onbattle.vkBot.commands.impl.profile.register;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.Command;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.core.VKManager;
import ru.onbattle.vkBot.dao.domain.University;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.dao.service.UniversityService;
import ru.onbattle.vkBot.flows.LayerFactory;

import java.util.Collection;

/**
 * @author abnormes on 27.05.2020
 * @project vkBot
 */
public class RegisterStep4 extends Command {

    public RegisterStep4(String name, State state) {
        super(name, state);
    }

    private synchronized boolean getUniversities(University university, String universityName) {
        Collection<University> universities;
        universities = new UniversityService().getAll();

        boolean isUniversity = false;

        for (University elem : universities) {
            if (universityName.equals(elem.getName())) {
                university.setId(elem.getId());
                university.setName(elem.getName());
                isUniversity = true;
            }
        }
        return isUniversity;
    }

    @Override
    public void exec(Message message) {
        Runnable readingThread = () -> {
            String universityName = message.getText();
            University university = new University();

            boolean isUniversity = getUniversities(university, universityName);

            if (isUniversity) {
                User user = User.getGuestById(message.getFromId());
                user.setUniversity(university);

                VKManager.sendKeyboard("Так ты из " + universityName + "?\nВ какой игре будешь обучаться?.",
                        message.getPeerId(),
                        LayerFactory.getKeyboard(LayerFactory.getGameLayer()));


                User.getGuestById(message.getFromId()).setState(State.REGISTER_5);
            } else {
                VKManager.sendKeyboard("Прости, но я не слышал о таком универе, попробуй снова",
                        message.getPeerId(),
                        LayerFactory.getKeyboard(LayerFactory.getUniversityLayer()));

                User.getGuestById(message.getFromId()).setState(State.REGISTER_4);
            }
        };

        new Thread(readingThread).start();
    }
}