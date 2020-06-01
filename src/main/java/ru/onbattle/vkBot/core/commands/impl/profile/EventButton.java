package ru.onbattle.vkBot.core.commands.impl.profile;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.flows.FlowFactory;
import ru.onbattle.vkBot.core.flows.subflows.impl.EventSubflow;
import ru.onbattle.vkBot.dao.domain.Event;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.vk.VKManager;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class EventButton extends CommandWithButton {

    private volatile int count = 0;

    public EventButton(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {

        Runnable thread = () -> {
            Collection<Event> events = EventSubflow.getEvents();
            StringBuilder buffer = new StringBuilder();

            for (Event event : events) {
                if (event.getDate().isBefore(OffsetDateTime.now())) {
                    String row =
                            String.format("%d. \"%s\" %s", ++count, event.getName(), event.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
                    buffer.append(row + "\n");
                }
            }

            count = 0;

            VKManager.sendKeyboard("Прошедшие турниры: \n\n" + buffer.toString(),
                    message.getPeerId(),
                    FlowFactory.getKeyboard(FlowFactory.getEventFlow()));

            User.getGuestById(message.getFromId()).setState(State.EVENT);
        };
        new Thread(thread).start();
    }
}
