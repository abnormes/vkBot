package ru.onbattle.vkBot.core.flows.subflows.impl;

import ru.onbattle.vkBot.core.flows.FlowFactory;
import ru.onbattle.vkBot.core.flows.subflows.Subflow;
import ru.onbattle.vkBot.dao.domain.Event;
import ru.onbattle.vkBot.dao.service.EventService;
import ru.onbattle.vkBot.util.SetButton;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author abnormes on 31.05.2020
 * @project vkBot
 */
public class EventSubflow extends Subflow {

    public static Collection getEvents() {
        return new EventService().getAll();
    }

    @Override
    public void run() {
        Collection<Event> events = getEvents();
        List<String> eventList = new ArrayList<>();

        for (Event event : events) {
            if (event.getDate().isAfter(OffsetDateTime.now())) {
                eventList.add(event.getName());
            }
        }
        FlowFactory.addButtonsToFlow(FlowFactory.getEventFlow(), SetButton.setButton(eventList, 2));
    }
}
