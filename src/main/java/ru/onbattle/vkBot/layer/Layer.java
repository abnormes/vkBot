package ru.onbattle.vkBot.layer;

import com.vk.api.sdk.objects.messages.KeyboardButton;
import ru.onbattle.vkBot.commands.CommandWithButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Flow main object
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class Layer {

    /** @param buttons keyboard buttons */
    private final List<List<KeyboardButton>> buttons;

    public Layer() {
        buttons = new ArrayList<>();
    }

    public List<List<KeyboardButton>> getButtons() {
        return buttons;
    }

    public Boolean addCommand(CommandWithButton command) {
        return buttons.add(command.getButton());
    }

    public Boolean addCommands(List<CommandWithButton> commands) {
        for(CommandWithButton command : commands) {
            if (!addCommand(command)) return false;
        }
        return true;
    }

    public Boolean addButton(List<KeyboardButton> button) {
        for (List<KeyboardButton> list : buttons) {
            String label = button.get(0).getAction().getLabel();
            String currentLabel = list.get(0).getAction().getLabel();
            if (label.equals(currentLabel)) {
                return false;
            }
        }
        return buttons.add(button);
    }

    public Boolean addButtons(List<List<KeyboardButton>> buttons) {
        for(List<KeyboardButton> button : buttons) {
            if (!addButton(button))
                return false;
        }
        return true;
    }

    public Boolean setButton(List<KeyboardButton> button) {
        if (buttons.isEmpty()) {
            return addButton(button);
        } else {
            this.buttons.clear();
            if (!addButton(button))
                return false;
        }
        return true;
    }

    public Boolean setButtons(List<List<KeyboardButton>> buttons) {
        if (buttons.isEmpty()) {
            return addButtons(buttons);
        } else {
            this.buttons.clear();
            if (!addButtons(buttons))
                return false;
        }
        return true;
    }
}
