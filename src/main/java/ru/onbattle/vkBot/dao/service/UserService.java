package ru.onbattle.vkBot.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.onbattle.vkBot.core.CommandState;
import ru.onbattle.vkBot.dao.Dao;
import ru.onbattle.vkBot.dao.DataSource;
import ru.onbattle.vkBot.dao.domain.Task;
import ru.onbattle.vkBot.dao.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author abnormes on 29.05.2020
 * @project vkBot
 */
public class UserService implements Dao<User, Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Override
    public User get(Integer id) {
        String sql = "SELECT * FROM users WHERE user_id = " + id;
        User user = new User();

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("user_id"));
                    user.setName(resultSet.getString("user_name"));
                    user.setRating(resultSet.getInt("user_rating"));
                    user.setUser(true);
                    user.setCommandState(CommandState.MAIN);
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public void save(User object) {
        String sql = "INSERT INTO "
                + "users (user_id, user_name, user_rating, univer_id) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, object.getId());
                statement.setString(2, object.getName());
                statement.setInt(3, object.getRating());
                statement.setInt(4, object.getUniversity().getId());
                statement.execute();
                LOGGER.info("Row for user " + object.getName() + " created successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(User object) {

    }

    @Override
    public void delete(User object) {

    }
}
