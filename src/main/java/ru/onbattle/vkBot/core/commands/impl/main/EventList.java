package ru.onbattle.vkBot.core.commands.impl.main;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.CommandState;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.dao.service.EventService;
import ru.onbattle.vkBot.vk.VKManager;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class EventList extends CommandWithButton {


    public EventList(String name, CommandState commandState) {
        super(name, commandState);
    }

    @Override
    public void exec(Message message) {
        Runnable readingThread = () -> {
            EventService eventService = new EventService();
            String events = String.valueOf(eventService.getAll());
            VKManager.sendMessage(events, message.getPeerId());
        };

        new Thread(readingThread).start();
    }
}
