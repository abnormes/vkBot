package ru.onbattle.vkBot.vk;

import com.vk.api.sdk.callback.CallbackApi;
import com.vk.api.sdk.objects.messages.Message;

/**
 * @author abnormes on 27.05.2020
 * @project vkBot
 */
public class VkCallbackApi extends CallbackApi {

    @Override
    public void messageNew(Integer groupId, Message message) {
        super.messageNew(groupId, message);
    }
}
