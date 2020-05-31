package ru.onbattle.vkBot.core.flows;

import ru.onbattle.vkBot.core.flows.subflows.Subflow;
import ru.onbattle.vkBot.core.flows.subflows.impl.EventSubflow;
import ru.onbattle.vkBot.core.flows.subflows.impl.NameSubflow;
import ru.onbattle.vkBot.core.flows.subflows.impl.UniversitySubflow;

import java.util.List;

/**
 * @author abnormes on 31.05.2020
 * @project vkBot
 */
public class FlowConfig {

    private Subflow eventSubflow = new EventSubflow();
    private Subflow universitySubflow = new UniversitySubflow();
    private Subflow nameSubflow = new NameSubflow();

    public FlowConfig() {
        eventSubflow.start();
        universitySubflow.start();
    }

    public synchronized void init(Flow flow, List commands) {
        try {
            eventSubflow.join();
            universitySubflow.join();
            FlowFactory.addCommandsToFlow(flow, commands);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
