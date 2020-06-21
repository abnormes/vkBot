package ru.onbattle.vkBot.core;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.CommandExecutor;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.dao.service.UserService;

public class VKMessenger implements Runnable {

    private final Message message;

    public VKMessenger(Message message) {
        this.message = message;
    }

    /**
     * Runnable object
     * Adding new users to runtime collection and checking guest for his role
     * Executing message
     */
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