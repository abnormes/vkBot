package ru.onbattle.vkBot.commands.impl.profile.student;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.CommandWithButton;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.core.VKManager;
import ru.onbattle.vkBot.dao.domain.Task;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.dao.service.TaskService;
import ru.onbattle.vkBot.layer.LayerFactory;
import ru.onbattle.vkBot.util.SetButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author abnormes on 29.05.2020
 * @project vkBot
 */
public class TaskButton extends CommandWithButton {

    public TaskButton(String name, State state) {
        super(name, state);
    }

    private synchronized void getTasks(Collection<Task> tasks) {
        List<String> list = new ArrayList<>();
        for (Task task : tasks) {
            list.add(task.getName());
        }
        LayerFactory.getTaskLayer().addButtons(SetButton.setButton(list, 2));
    }

    @Override
    public void exec(Message message) {
        Runnable readingThread = () -> {
            TaskService taskService = new TaskService();
            Collection<Task> tasks = taskService.getAllById(message.getFromId());

            getTasks(tasks);
            User user = User.getGuestById(message.getFromId());
            HashMap<String, Task> taskHashMap = new HashMap<>();
            for (Task elem : tasks) {
                taskHashMap.put(elem.getName(), elem);
            }
            user.setTaskList(taskHashMap);

            if (tasks.isEmpty()) {
                VKManager.sendMessage("Для вас нет задач", message.getPeerId());
            } else {
                VKManager.sendKeyboard("Ваши задачи на сегодня", message.getPeerId(),
                        LayerFactory.getKeyboard(LayerFactory.getTaskLayer()));

                User.getGuestById(message.getFromId()).setState(State.TASK_1);
            }
        };

        new Thread(readingThread).start();
    }
}
