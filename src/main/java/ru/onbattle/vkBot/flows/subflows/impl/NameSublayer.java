package ru.onbattle.vkBot.flows.subflows.impl;

import ru.onbattle.vkBot.flows.LayerFactory;
import ru.onbattle.vkBot.flows.subflows.Sublayer;
import ru.onbattle.vkBot.util.SetButton;

import java.util.List;

/**
 * @author abnormes on 31.05.2020
 * @project vkBot
 */
public class NameSublayer implements Sublayer {

    public NameSublayer() {
        LayerFactory.addButtonsToFlow(LayerFactory.getNameLayer(), SetButton.setButton(List.of("Да", "Хочу другое имя"), 2));
    }

    @Override
    public void run() {

    }
}
