package ru.onbattle.vkBot.commands.impl.main;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.CommandWithButton;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.core.VKManager;

/**
 * @author abnormes on 31.05.2020
 * @project vkBot
 */
public class About extends CommandWithButton {

    public About(String name, State state) { super(name, state); }

    @Override
    public void exec(Message message) {
        String about = "В разработке";

        VKManager.sendMessage(about, message.getPeerId());
    }
}
