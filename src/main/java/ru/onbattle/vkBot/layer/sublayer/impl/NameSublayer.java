package ru.onbattle.vkBot.layer.sublayer.impl;

import ru.onbattle.vkBot.layer.LayerFactory;
import ru.onbattle.vkBot.layer.sublayer.Sublayer;
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
