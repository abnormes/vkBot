package ru.onbattle.vkBot.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.onbattle.vkBot.dao.Dao;
import ru.onbattle.vkBot.dao.DataSource;
import ru.onbattle.vkBot.dao.domain.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author abnormes on 10.06.2020
 * @project vkBot
 */
public class GameService implements Dao<Game, Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    @Override
    public Game get(Integer integer) {
        return null;
    }

    @Override
    public Collection<Game> getAll() {
        String sql = "SELECT * FROM games";
        List<Game> games = new ArrayList<>();

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()) {
                    Game game = new Game();
                    game.setId(resultSet.getInt("game_id"));
                    game.setName(resultSet.getString("game_name"));
                    games.add(game);
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOGGER.info("Founded " + games.size() + " rows");

        return games;
    }

    @Override
    public void save(Game object) {

    }

    @Override
    public void update(Game object) {

    }

    @Override
    public void delete(Game object) {

    }
}
