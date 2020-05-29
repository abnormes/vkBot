package ru.onbattle.vkBot.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.onbattle.vkBot.dao.Dao;
import ru.onbattle.vkBot.dao.DataSource;
import ru.onbattle.vkBot.dao.domain.University;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author abnormes on 29.05.2020
 * @project vkBot
 */
public class UniversityService implements Dao<University, Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversityService.class);

    @Override
    public University get(Integer id) {
        return null;
    }

    @Override
    public Collection<University> getAll() {

        String sql = "SELECT * FROM university";
        List<University> universities = new ArrayList<>();

        try (Connection connection = DataSource.getConnection()) {

            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()) {
                    University university = new University();
                    university.setId(resultSet.getInt("univer_id"));
                    university.setName(resultSet.getString("univer_name"));
                    universities.add(university);
                }
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOGGER.info("Founded " + universities.size() + " rows");

        return universities;
    }

    @Override
    public void save(University object) {
    }

    @Override
    public void update(University object) {

    }

    @Override
    public void delete(University object) {

    }
}
