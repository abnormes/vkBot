package ru.onbattle.vkBot.core.flows.subflows.impl;

import ru.onbattle.vkBot.core.flows.FlowFactory;
import ru.onbattle.vkBot.core.flows.subflows.Subflow;
import ru.onbattle.vkBot.dao.domain.University;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.dao.service.UniversityService;
import ru.onbattle.vkBot.util.SetButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author abnormes on 31.05.2020
 * @project vkBot
 */

public class UniversitySubflow extends Subflow {

    @Override
    public void run() {

        UniversityService universityService = new UniversityService();
        Collection<University> universities = universityService.getAll();
        List<String> uniNames = new ArrayList<>();

        for (University university : universities) {
            uniNames.add(university.getName());
        }

        FlowFactory.setButtonsToFlow(
                FlowFactory.getUniversityFlow(),
                SetButton.setButton(uniNames, 3));
    }
}
