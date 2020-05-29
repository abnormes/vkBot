package ru.onbattle.vkBot.core;

import com.vk.api.sdk.objects.messages.KeyboardButton;

import java.util.List;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public abstract class CommandWithButton extends Command {

    private final List<KeyboardButton> button;

    public CommandWithButton(String name,
                             State state,
                             List<KeyboardButton> button) {
        super(name, state);
        this.button = button;
    }

    public List<KeyboardButton> getButton() { return button; }
}
