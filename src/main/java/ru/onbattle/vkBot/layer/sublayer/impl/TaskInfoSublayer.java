package ru.onbattle.vkBot.layer.sublayer.impl;

import ru.onbattle.vkBot.layer.LayerFactory;
import ru.onbattle.vkBot.layer.sublayer.Sublayer;
import ru.onbattle.vkBot.util.SetButton;

import java.util.List;

/**
 * @author abnormes on 13.06.2020
 * @project vkBot
 */
public class TaskInfoSublayer implements Sublayer {

    public TaskInfoSublayer() {
        LayerFactory.addButtonsToFlow(LayerFactory.getTaskInfoLayer(),
                SetButton.setButton(List.of("Написать отчет о выполнение"), 1));
    }

    @Override
    public void run() {
    }
}
