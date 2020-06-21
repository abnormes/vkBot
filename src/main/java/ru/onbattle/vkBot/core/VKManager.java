package ru.onbattle.vkBot.core;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import ru.onbattle.vkBot.util.GetRandomId;

public class VKManager {

    // Singleton object VKCore
    private static final VKCore vkCore = VKCore.getInstance();

    private VKManager() {}

    /**
     * Method sending message to user
     * @param message response message to user
     * @param peerId id of conversation
     */
    public static void sendMessage(String message, int peerId){
        if (message == null){
            System.out.println("message is empty");
            return;
        }
        try {
            vkCore.getVk()
                    .messages()
                    .send(vkCore.getActor())
                    .peerId(peerId).message(message)
                    .randomId(GetRandomId.getRandomId())
                    .execute();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method sending keyboard and message to user
     * @param message response to user
     * @param peerId conversation id
     * @param keyboard keyboard
     */
    public static void sendKeyboard(String message, int peerId, Keyboard keyboard){
        if (keyboard == null) {
            System.out.println("keyboard is empty");
            return;
        }
        try {
            vkCore.getVk()
                    .messages()
                    .send(vkCore.getActor())
                    .peerId(peerId)
                    .message(message)
                    .keyboard(keyboard)
                    .randomId(GetRandomId.getRandomId())
                    .execute();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * VK API returning information about user by his id
     * @param id vk user identificator
     * @return {@link UserXtrCounters} information about user
     * @see UserXtrCounters
     */
    public static UserXtrCounters getUserInfo(int id){
        try {
            return vkCore.getVk().users()
                    .get(vkCore.getActor())
                    .userIds(String.valueOf(id))
                    .execute()
                    .get(0);
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

}
