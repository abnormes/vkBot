package ru.onbattle.vkBot.commands.impl.profile.student.tasks;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.Command;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.core.VKManager;
import ru.onbattle.vkBot.dao.domain.Task;
import ru.onbattle.vkBot.dao.domain.User;

/**
 * @author abnormes on 14.06.2020
 * @project vkBot
 */
public class TaskStep2 extends Command {

    public TaskStep2(String name, State state) {
        super(name, state);
    }

    @Override
    public void exec(Message message) {
        if (message.getText().equals("Написать отчет о выполнение")) {
            VKManager.sendMessage("Введите ваш отчет", message.getPeerId());
        } else {

            Runnable thread = () -> {
//                User user = User.getGuestById(message.getFromId());
//                Task task =  user.getTaskList().get();
//                task.setResult(message.getText());
//                user.getTaskList().remove();
//                task.getType()
            };
            new Thread(thread).start();
        }

    }
}
