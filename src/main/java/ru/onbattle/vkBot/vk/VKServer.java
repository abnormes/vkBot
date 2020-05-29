package ru.onbattle.vkBot.vk;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.onbattle.vkBot.dao.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VKServer {
    private static VKCore vkCore = VKCore.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(VKServer.class);

    public static void serverStart() throws ApiException, InterruptedException {
        LOGGER.debug("Server started");
        try (Connection connection = DataSource.getConnection()) {
            LOGGER.debug("Database connected");
        } catch (SQLException e) {
            LOGGER.error("DATABASE IS NOT CONNECTED!!!", e);
        }
        while (true) {
            Thread.sleep(300);

            try {
                Message message = vkCore.getMessage();
                if (message != null) {
                    ExecutorService exec = Executors.newCachedThreadPool();
                    exec.execute(new VkMessenger(message));
                }
            } catch (ClientException e) {
                LOGGER.error("CONNECTION PROBLEM");
                final int RECONNECT_TIME = 10000;
                System.out.println("Retry after " + RECONNECT_TIME / 1000 + " seconds");
                Thread.sleep(RECONNECT_TIME);
            }
        }
    }
}