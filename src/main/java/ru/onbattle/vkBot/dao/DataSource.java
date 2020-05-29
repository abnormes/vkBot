package ru.onbattle.vkBot.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author abnormes on 25.05.2020
 * @project vkBot
 */
public class DataSource {

    // Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    // Hikari connection
    private static HikariConfig config;
    private static HikariDataSource ds;

    static {
        Properties prop = new Properties();
        try {
            prop.load(DataSource.class.getResourceAsStream("/hikari.properties"));
            config = new HikariConfig(prop);
            ds = new HikariDataSource(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DataSource(){}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
