package ru.onbattle.vkBot.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.onbattle.vkBot.dao.Dao;
import ru.onbattle.vkBot.dao.DataSource;
import ru.onbattle.vkBot.dao.domain.Task;

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
public class TaskService implements Dao<Task, Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    @Override
    public Task get(Integer id) {
        return null;
    }

    @Override
    public Collection<Task> getAll() {
        return null;
    }

    @Override
    public void save(Task object) {
    }

    @Override
    public void update(Task object) {

    }

    @Override
    public void delete(Task object) {

    }

    public Collection<Task> getAllById(int id) {
        Collection<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE user_id = " + id + " AND task_status = false";

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setId(resultSet.getLong("task_id"));
                    task.setName(resultSet.getString("task_name"));
                    task.setDescription(resultSet.getString("task_description"));
                    task.setActive(resultSet.getBoolean("task_status"));
                    tasks.add(task);
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOGGER.info("Founded " + tasks.size() + " rows");

        return tasks;
    }
}
