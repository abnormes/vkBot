package ru.onbattle.vkBot.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.onbattle.vkBot.dao.DataSource;
import ru.onbattle.vkBot.dao.Dao;
import ru.onbattle.vkBot.dao.domain.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author abnormes on 26.05.2020
 * @project vkBot
 */
public class EventService implements Dao<Event, Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    @Override
    public Optional<Event> get(int id) {
        return Optional.empty();
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
                    event.setId(resultSet.getLong("event_id"));
                    event.setName(resultSet.getString("event_name"));
                    event.setDate(resultSet.getObject("event_date", OffsetDateTime.class));
                    event.setDescription(resultSet.getString("event_description"));
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
    public Optional<Integer> save(Event object) {
        return Optional.empty();
    }

    @Override
    public void update(Event object) {

    }

    @Override
    public void delete(Event object) {

    }
}
