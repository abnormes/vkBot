package ru.onbattle.vkBot.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public final class VKCore {

    private static final Logger LOGGER = LoggerFactory.getLogger(VKCore.class);
    private static VKCore instance;
    private VkApiClient vk;
    private GroupActor actor;
    private static int ts;
    private static int maxMsgId = -1;

    private VKCore() throws ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);

        // Загрузка конфигураций
        Properties prop = new Properties();
        int groupId;
        String access_token;
        try{
            prop.load(VKCore.class.getResourceAsStream("/application.properties"));
            groupId = Integer.valueOf(prop.getProperty("vk.group-id"));
            access_token = prop.getProperty("vk.token");
            actor = new GroupActor(groupId, access_token);

            ts = vk.messages().getLongPollServer(actor).execute().getTs();
        } catch (IOException e) {
            LOGGER.error("Failed to load configuration file");
        }
    }

    public static VKCore getInstance() {
        if (instance == null) {
            try {
                instance = new VKCore();
            } catch (ClientException | ApiException e) {
                LOGGER.error("Failed to create VKCore instance", e);
            }
        }
        return instance;
    }

    public GroupActor getActor() {
        return actor;
    }

    public VkApiClient getVk() {
        return vk;
    }

    public Message getMessage() throws ClientException, ApiException {

        MessagesGetLongPollHistoryQuery eventsQuery = vk.messages()
                .getLongPollHistory(actor)
                .ts(ts);
        if (maxMsgId > 0) {
            eventsQuery.maxMsgId(maxMsgId);
        }
        List<Message> messages = eventsQuery
                .execute()
                .getMessages()
                .getItems();

        if (!messages.isEmpty()) {
            try {
                ts = vk.messages()
                        .getLongPollServer(actor)
                        .execute()
                        .getTs();
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }
        if (!messages.isEmpty() && !messages.get(0).isOut()) {

                /*
                    messageId - максимально полученный ID, нужен, чтобы не было ошибки 10 internal server error,
                    который является ограничением в API VK. В случае, если ts слишком старый (больше суток),
                    а max_msg_id не передан, метод может вернуть ошибку 10 (Internal server error).
                 */
            int messageId = messages.get(0).getId();
            if (messageId > maxMsgId) {
                maxMsgId = messageId;
            }
            return messages.get(0);
        }
        return null;
    }

}

