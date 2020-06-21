package ru.onbattle.vkBot.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.dao.Dao;
import ru.onbattle.vkBot.dao.DataSource;
import ru.onbattle.vkBot.dao.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
                    setUser(resultSet, user);
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
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    setUser(resultSet, user);
                    users.add(user);
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOGGER.info("Founded " + users.size() + " rows");
        return users;
    }

    private void setUser(ResultSet resultSet, User user) throws SQLException {
        user.setId(resultSet.getInt("user_id"));
        user.setName(resultSet.getString("user_name"));
        user.setRating(resultSet.getInt("user_rating"));
        user.setGame(new GameService().get(resultSet.getInt("game_id")));
        user.setUniversity(new UniversityService().get(resultSet.getInt("univer_id")));
        user.setUser(true);
        user.setState(State.MAIN);
    }

    @Override
    public void save(User object) {
        String sql = "INSERT INTO "
                + "users (user_id, user_name, user_rating, univer_id, game_id, user_is_active) "
                + "VALUES (?, ?, ?, ?, ?, ?);"
                + "INSERT INTO "
                + "user_roles (user_user_id, user_roles) "
                + "VALUES (?, ?);";

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, object.getId());
                statement.setString(2, object.getName());
                statement.setInt(3, object.getRating());
                if (object.getUniversity() == null) {
                    statement.setNull(4, Types.NULL);
                } else {
                    statement.setInt(4, object.getUniversity().getId());
                }
                if (object.getGame() == null) {
                    statement.setNull(5, Types.NULL);
                } else {
                    statement.setInt(5, object.getGame().getId());
                }
                statement.setBoolean(6, object.isActive());
                statement.setInt(7, object.getId());
                statement.setString(8, object.getRoles().toString());
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
        String sql = "UPDATE users "
                + "SET "
                + "user_name = ?, "
                + "univer_id = ?, "
                + "game_id = ?, "
                + "user_is_active = ? "
                + "WHERE "
                + "user_id = " + object.getId() + "; "
                + "UPDATE user_roles "
                + "SET "
                + "user_user_id = ?,"
                + "user_roles = ?;";

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, object.getName());
                if (object.getUniversity() == null) {
                    statement.setNull(2, Types.NULL);
                } else {
                    statement.setInt(2, object.getUniversity().getId());
                }
                if (object.getGame() == null) {
                    statement.setNull(3, Types.NULL);
                } else {
                    statement.setInt(3, object.getGame().getId());
                }
                statement.setBoolean(4, object.isActive());
                statement.setInt(5, object.getId());
                statement.setString(6, object.getRoles().toString());
                statement.execute();
                LOGGER.info("Row for user " + object.getName() + " updated successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User object) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try(Connection connection = DataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, object.getId());
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
