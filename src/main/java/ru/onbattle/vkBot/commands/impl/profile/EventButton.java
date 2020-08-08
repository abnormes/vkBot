package ru.onbattle.vkBot.commands.impl.profile;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.CommandWithButton;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.core.VKManager;
import ru.onbattle.vkBot.dao.domain.Event;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.layer.LayerFactory;
import ru.onbattle.vkBot.layer.sublayer.impl.EventSublayer;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class EventButton extends CommandWithButton {

    private volatile AtomicInteger count = new AtomicInteger(0);

    public EventButton(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {

        Runnable thread = () -> {
            Collection<Event> events = EventSublayer.getEvents();
            StringBuilder buffer = new StringBuilder();

            for (Event event : events) {
                if (event.getDate().isBefore(OffsetDateTime.now())) {
                    String row =
                            String.format("%d. \"%s\" %s\n", count.incrementAndGet(), event.getName(), event.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
                    buffer.append(row);
                }
            }

            count.set(0);

            VKManager.sendKeyboard("Прошедшие турниры: \n\n" + buffer.toString(),
                    message.getPeerId(),
                    LayerFactory.getKeyboard(LayerFactory.getEventLayer()));

            User.getGuestById(message.getFromId()).setState(State.EVENT);
        };
        new Thread(thread).start();
    }
}
