package ru.onbattle.vkBot.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.onbattle.vkBot.dao.Dao;
import ru.onbattle.vkBot.dao.DataSource;
import ru.onbattle.vkBot.dao.domain.Task;
import ru.onbattle.vkBot.dao.domain.TaskType;

import java.sql.*;
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
        String sql = "UPDATE tasks "
                + "SET "
                + "task_staus = ?, "
                + "WHERE "
                + "task_id = " + object.getId();

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1, object.getActive());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Task object) {

    }

    public Collection<Task> getAllById(int id) {
        Collection<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE user_id = " + id +
                " AND task_status = false AND (task_date = CURRENT_DATE OR " +
                "task_type = 1)";

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setId(resultSet.getLong("task_id"));
                    task.setName(resultSet.getString("task_name"));
                    task.setType((resultSet.getInt("task_type") == 0) ? TaskType.EVERY_DAY : TaskType.ONE_TIME);
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

    public Task getTaskByName(int id, String name) {
        Task task = new Task();
        String sql =
                "SELECT * FROM tasks" +
                        " WHERE user_id = " + id +
                        " AND task_status = false" +
                        " AND (task_date = CURRENT_DATE OR task_type = 1)" +
                        " AND task_name = \'" + name +
                        "\' LIMIT 1";

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                ResultSet resultSet = stm.executeQuery();
                if (resultSet.next()) {
                    task.setId(resultSet.getLong("task_id"));
                    task.setName(resultSet.getString("task_name"));
                    task.setDescription(resultSet.getString("task_description"));
                    task.setActive(resultSet.getBoolean("task_status"));
                }

                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }
}
