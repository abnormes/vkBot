package ru.onbattle.vkBot.layer.sublayer.impl;

import ru.onbattle.vkBot.layer.LayerFactory;
import ru.onbattle.vkBot.layer.sublayer.Sublayer;
import ru.onbattle.vkBot.dao.domain.University;
import ru.onbattle.vkBot.dao.service.UniversityService;
import ru.onbattle.vkBot.util.SetButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author abnormes on 31.05.2020
 * @project vkBot
 */

public class UniversitySublayer implements Sublayer {

    @Override
    public void run() {

        UniversityService universityService = new UniversityService();
        Collection<University> universities = universityService.getAll();
        List<String> uniNames = new ArrayList<>();

        for (University university : universities) {
            uniNames.add(university.getName());
        }

        LayerFactory.setButtonsToFlow(
                LayerFactory.getUniversityLayer(),
                SetButton.setButton(uniNames, 3));
    }
}
