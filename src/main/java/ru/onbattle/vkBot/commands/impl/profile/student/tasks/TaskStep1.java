package ru.onbattle.vkBot.commands.impl.profile.student.tasks;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.Command;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.core.VKManager;
import ru.onbattle.vkBot.dao.domain.Task;
import ru.onbattle.vkBot.dao.domain.TaskType;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.dao.service.TaskService;
import ru.onbattle.vkBot.flows.LayerFactory;

/**
 * @author abnormes on 13.06.2020
 * @project vkBot
 */
public class TaskStep1 extends Command {

    public TaskStep1(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {

        if (message.getText().equals("Ваш профиль")) {
            VKManager.sendKeyboard("Ваш профиль", message.getFromId(), LayerFactory.getKeyboard(LayerFactory.getRegisteredLayer()));
            User.getGuestById(message.getFromId()).setState(State.PROFILE_REGISTERED);
        } else {
            Task task = new TaskService().getTaskByName(message.getFromId(), message.getText());
            if (task.getName() != null) {
                String status = task.getActive() ? "Выполнен" : "В процессе";
                String type = task.getType() == TaskType.EVERY_DAY ? "Ежедневный" : "Единовременный";
                String text = "Информация о задаче: "
                        + task.getDescription()
                        + "\nСтатус задачи: "
                        + status
                        + "\nТип задачи: "
                        + type;
                VKManager.sendKeyboard(text, message.getFromId(), LayerFactory.getKeyboard(LayerFactory.getTaskInfoLayer()));
                User.getGuestById(message.getFromId()).setState(State.TASK2);
            } else {
                VKManager.sendMessage("Простите, такой задачи нет или она уже выполнена", message.getPeerId());
            }

        }
    }
}
