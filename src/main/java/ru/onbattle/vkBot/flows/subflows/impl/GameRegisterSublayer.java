package ru.onbattle.vkBot.flows.subflows.impl;

import ru.onbattle.vkBot.dao.domain.Game;
import ru.onbattle.vkBot.dao.service.GameService;
import ru.onbattle.vkBot.flows.LayerFactory;
import ru.onbattle.vkBot.flows.subflows.Sublayer;
import ru.onbattle.vkBot.util.SetButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author abnormes on 10.06.2020
 * @project vkBot
 */
public class GameRegisterSublayer implements Sublayer {
    @Override
    public void run() {
        GameService gameService = new GameService();
        Collection<Game> games = gameService.getAll();
        List<String> gameNames = new ArrayList<>();

        for (Game game : games) {
            gameNames.add(game.getName());
        }

        LayerFactory.setButtonsToFlow(
                LayerFactory.getGameLayer(),
                SetButton.setButton(gameNames, 2));
    }
}
