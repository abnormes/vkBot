package ru.onbattle.vkBot.core;

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

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public final class VKCore {

    /**
     * @param LOGGER logger
     * @param instance VKCore instance
     * @param vk functional from VK API SDK
     * @param actor instance of vk group
     * @param ts VK begging message identificator
     * @param maxMsgId VK message identificator
     * @param groupId vk group identificator
     * @param accessToken vk group access token
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(VKCore.class);
    private static volatile VKCore instance;
    private final VkApiClient vk;
    private GroupActor actor;
    private int ts;
    private int maxMsgId = -1;

    /**
     * Singleton object
     *
     * @throws ClientException
     * @throws ApiException
     */
    private VKCore() throws ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);

        // Load configuration
        instanceConfigure();
    }

    /**
     * Load configure from application.properties
     * @throws ClientException
     * @throws ApiException
     */
    private void instanceConfigure() throws ClientException, ApiException {
        Properties prop = new Properties();
        int groupId;
        String accessToken;

        try{
            prop.load(VKCore.class.getResourceAsStream("/application.properties"));
            groupId = Integer.valueOf(prop.getProperty("vk.group-id"));
            accessToken = prop.getProperty("vk.token");

            actor = new GroupActor(groupId, accessToken);
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
        } catch (IOException e) {
            LOGGER.error("Failed to load configuration file");
        }
    }

    /**
     * Synchronized method
     *
     * @return VKCore instance
     */
    public static VKCore getInstance() {
        VKCore localInstance = instance;
        if (localInstance == null) {
            synchronized (VKCore.class) {
                try {
                    localInstance = instance;
                    if (localInstance == null) {
                        instance = localInstance = new VKCore();
                    }
                } catch (ClientException | ApiException e) {
                    LOGGER.error("Failed to create VKCore instance", e);
                }
            }
        }
        return instance;
    }

    /**
     * Return functions for group actor
     * @return GroupActor
     */
    public GroupActor getActor() {
        return actor;
    }

    /**
     * Return functions for vk
     * @return VkApiClient
     */
    public VkApiClient getVk() {
        return vk;
    }


    /**
     * Method for message handle
     * @return Message
     * @throws ClientException
     * @throws ApiException
     */
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
            return messages.get(0)  ;
        }
        return null;
    }

}

