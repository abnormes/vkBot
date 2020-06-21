package ru.onbattle.vkBot.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.onbattle.vkBot.dao.Dao;
import ru.onbattle.vkBot.dao.DataSource;
import ru.onbattle.vkBot.dao.domain.Event;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author abnormes on 26.05.2020
 * @project vkBot
 */
public class EventService implements Dao<Event, Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    @Override
    public Event get(Integer id) {
        String sql = "SELECT * FROM events WHERE event_id = " + id;
        Event event = new Event();

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()) {
                    event.setId(resultSet.getInt("event_id"));
                    event.setName(resultSet.getString("event_name"));
                    event.setDate(resultSet.getObject("event_date", OffsetDateTime.class));
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return event;
    }

    @Override
    public Collection<Event> getAll() {
        String sql = "SELECT * FROM events";
        List<Event> events = new ArrayList<>();

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()) {
                    Event event = new Event();
                    event.setId(resultSet.getInt("event_id"));
                    event.setName(resultSet.getString("event_name"));
                    event.setDate(resultSet.getObject("event_date", OffsetDateTime.class));
                    events.add(event);
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOGGER.info("Founded " + events.size() + " rows");

        return events;
    }

    @Override
    public void save(Event object) {
        String sql = "INSERT INTO "
                + "events (event_name, event_date) "
                + "VALUES (?, ?)";

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, object.getName());
                statement.setObject(2, object.getDate());

                statement.execute();
                LOGGER.info("Row for event " + object.getName() + " created successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Event object) {
        String sql = "UPDATE events "
                + "SET "
                + "event_name = ?, "
                + "event_date = ?, "
                + "WHERE "
                + "event_id = " + object.getId();

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, object.getName());
                if (object.getDate() == null) {
                    statement.setNull(2, Types.NULL);
                } else {
                    statement.setObject(2, object.getDate());
                }
                statement.execute();
                LOGGER.info("Row for event " + object.getName() + " updated successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Event object) {
        String sql = "DELETE FROM events WHERE event_name = ?";

        try(Connection connection = DataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, object.getName());
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
