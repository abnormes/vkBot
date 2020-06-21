package ru.onbattle.vkBot.flows;

import ru.onbattle.vkBot.commands.Command;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.commands.impl.Unknown;
import ru.onbattle.vkBot.commands.impl.profile.register.*;
import ru.onbattle.vkBot.commands.impl.profile.student.tasks.TaskStep1;

/**
 * @author abnormes on 09.06.2020
 * @project vkBot
 */
public class LayerDeterminant {

    public static Command detectSubLayer(State userState) {
        // Register sublayer
        if (userState.equals(State.REGISTER)) {
            return new RegisterStep1("register_one", State.REGISTER);
        } else if (userState.equals(State.REGISTER_3)) {
            return new RegisterStep3("register_two", State.REGISTER);
        } else if (userState.equals(State.REGISTER_2)) {
            return new RegisterStep2("register_three", State.REGISTER);
        } else if (userState.equals(State.REGISTER_4)) {
            return new RegisterStep4("register_four", State.REGISTER);
        } else if (userState.equals(State.REGISTER_5)) {
            return new RegisterStep5("register_five", State.REGISTER);
        }

        // Task sublayer
        if (userState.equals(State.TASK1)) {
            return new TaskStep1("task_one", State.TASK);
        } else if (userState.equals(State.TASK2)) {
            return new TaskStep1("task_two", State.TASK);
        }
        return new Unknown("unknown command", State.ALL);
    }

}
