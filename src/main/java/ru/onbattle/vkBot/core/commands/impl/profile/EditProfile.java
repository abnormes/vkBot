package ru.onbattle.vkBot.core.commands.impl.profile;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.State;

/**
 * @author abnormes on 31.05.2020
 * @project vkBot
 */
public class EditProfile extends CommandWithButton {

    public EditProfile(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        Register.register(message);
    }
}
