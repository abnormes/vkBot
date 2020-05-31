package ru.onbattle.vkBot.vk;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.CommandExecutor;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.dao.service.UserService;

public class VkMessenger implements Runnable {

    private Message message;

    public VkMessenger(Message message) {
        this.message = message;
    }

    @Override
    public void run() {

        if (!User.getGuests().containsKey(message.getFromId())) {
            User bufferUser = new UserService().get(message.getFromId());
            if (bufferUser.getName() == null) {
                User user = new User(message.getFromId(), State.MAIN, false);
                User.getGuests().put(message.getFromId(), user);
            } else {
                bufferUser.setUser(true);
                bufferUser.setState(State.MAIN);
                User.getGuests().put(message.getFromId(), bufferUser);
            }
        }
        CommandExecutor.execute(message);

    }
}