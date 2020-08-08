package ru.onbattle.vkBot.layer.sublayer.impl;

import ru.onbattle.vkBot.layer.LayerFactory;
import ru.onbattle.vkBot.layer.sublayer.Sublayer;
import ru.onbattle.vkBot.util.SetButton;

import java.util.List;

/**
 * @author abnormes on 09.06.2020
 * @project vkBot
 */
public class StudentSublayer implements Sublayer {

    public StudentSublayer() {
        LayerFactory.addButtonsToFlow(LayerFactory.getStudentRegistrationLayer(), SetButton.setButton(List.of("Да", "Нет"), 2));
    }

    @Override
    public void run() {

    }
}
