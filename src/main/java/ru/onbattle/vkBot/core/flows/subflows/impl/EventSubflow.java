package ru.onbattle.vkBot.core.flows.subflows.impl;

import ru.onbattle.vkBot.core.flows.FlowFactory;
import ru.onbattle.vkBot.core.flows.subflows.Subflow;
import ru.onbattle.vkBot.dao.domain.Event;
import ru.onbattle.vkBot.dao.service.EventService;
import ru.onbattle.vkBot.util.SetButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author abnormes on 31.05.2020
 * @project vkBot
 */
public class EventSubflow extends Subflow {

    @Override
    public void run() {
        Collection<Event> eventList = new EventService().getAll();
        List<String> events = new ArrayList<>();

        for (Event event : eventList) {
            events.add(event.getName());
        }
        FlowFactory.addButtonsToFlow(FlowFactory.getEventFlow(), SetButton.setButton(events, 1));
    }
}
