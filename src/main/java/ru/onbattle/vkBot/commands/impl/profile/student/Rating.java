package ru.onbattle.vkBot.commands.impl.profile.student;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.CommandWithButton;
import ru.onbattle.vkBot.commands.State;

/**
 * @author abnormes on 23.06.2020
 * @project vkBot
 */
public class Rating extends CommandWithButton {

    public Rating(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {

    }
}
