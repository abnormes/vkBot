package ru.onbattle.vkBot.core.keyboards;

import com.vk.api.sdk.objects.messages.KeyboardButton;
import ru.onbattle.vkBot.core.CommandWithButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class Flow {

    private List<List<KeyboardButton>> buttons;

    public Flow() {
        buttons = new ArrayList<>();
    }

    public List<List<KeyboardButton>> getButtons() {
        return buttons;
    }

    public Boolean addCommand(CommandWithButton command) {
        return buttons.add(command.getButton()) ? true : false;
    }

    public Boolean addCommands(List<CommandWithButton> commands) {
        for(CommandWithButton command : commands) {
            if (!addCommand(command)) return false;
        }
        return true;
    }

    public Boolean addButton(List<KeyboardButton> button) {
        return buttons.add(button) ? true : false;
    }

    public Boolean addButtons(List<List<KeyboardButton>> buttons) {
        for(List<KeyboardButton> button : buttons) {
            if (!addButton(button))
                return false;
        }
        return true;
    }
}
