package ru.onbattle.vkBot.core.commands.impl.profile.event;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.State;

/**
 * @author abnormes on 31.05.2020
 * @project vkBot
 */
public class EventList extends CommandWithButton {

    public EventList(String name, State state) {
        super(name, state);
    }


    @Override
    public void exec(Message message) {

     }
}
