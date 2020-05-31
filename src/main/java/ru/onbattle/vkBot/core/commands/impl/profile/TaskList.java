package ru.onbattle.vkBot.core.commands.impl.profile;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.core.State;
import ru.onbattle.vkBot.core.CommandWithButton;
import ru.onbattle.vkBot.dao.domain.Task;
import ru.onbattle.vkBot.dao.service.TaskService;
import ru.onbattle.vkBot.vk.VKManager;

import java.util.Collection;

/**
 * @author abnormes on 29.05.2020
 * @project vkBot
 */
public class TaskList extends CommandWithButton {

    public TaskList(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        Runnable readingThread = () -> {
            TaskService taskService = new TaskService();
            Collection<Task> tasks = taskService.getAllById(message.getFromId());

            if (tasks.isEmpty()) {
                VKManager.sendMessage("Для вас нет задач", message.getPeerId());
            } else {
                VKManager.sendMessage(tasks.toString(), message.getPeerId());
            }
        };

        new Thread(readingThread).start();
    }
}
