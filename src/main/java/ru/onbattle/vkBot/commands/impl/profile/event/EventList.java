package ru.onbattle.vkBot.commands.impl.profile.event;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.CommandWithButton;
import ru.onbattle.vkBot.commands.State;

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
