package ru.onbattle.vkBot.vk;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.Commander;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.dao.domain.Guest;

public class VkMessenger implements Runnable {

    private Message message;

    public VkMessenger(Message message) {
        this.message = message;
    }

    @Override
    public void run() {

        Boolean isGuest = false;
        if (Guest.getGuests().containsKey(message.getFromId())) {
            isGuest = true;
        }

        if (!isGuest) {
            Guest user = new Guest(message.getFromId(), State.MAIN, false);
            Guest.getGuests().put(message.getFromId(), user);
        }
        Commander.execute(message);

    }
}