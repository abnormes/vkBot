package ru.onbattle.vkBot.core.commands.impl.main;

import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.vk.VKManager;

import java.util.List;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class Hello extends CommandWithButton {

    public Hello(String name, List<KeyboardButton> button, State state) {
        super(name, state, button);
    }

    @Override
    public void exec(Message message) {
        VKManager.sendMessage("Привет", message.getPeerId());
    }
}
