package ru.onbattle.vkBot.core.flows.subflows.impl;

import ru.onbattle.vkBot.core.flows.FlowFactory;
import ru.onbattle.vkBot.core.flows.subflows.Subflow;
import ru.onbattle.vkBot.util.SetButton;

import java.util.List;

/**
 * @author abnormes on 31.05.2020
 * @project vkBot
 */
public class NameSubflow extends Subflow {

    public NameSubflow() {
        FlowFactory.addButtonsToFlow(FlowFactory.getNameFlow(), SetButton.setButton(List.of("Да", "Хочу другое имя"), 2));
    }
}
