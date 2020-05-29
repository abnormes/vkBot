package ru.onbattle.vkBot.core;

import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.KeyboardButtonAction;
import com.vk.api.sdk.objects.messages.KeyboardButtonActionType;
import com.vk.api.sdk.objects.messages.KeyboardButtonColor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public abstract class CommandWithButton extends Command {

    private List<KeyboardButton> button;
    // Button settings
    private KeyboardButtonActionType buttonType;
    private KeyboardButtonColor buttonColor;
    private String label;

    public CommandWithButton(String name,
                             CommandState commandState) {
        super(name, commandState);
        this.label = name;
        this.buttonType = KeyboardButtonActionType.TEXT;
        this.buttonColor = KeyboardButtonColor.DEFAULT;
        setButton();
    }

    public KeyboardButtonActionType getButtonType() {
        return buttonType;
    }

    public void setButtonType(KeyboardButtonActionType buttonType) {
        this.buttonType = buttonType;
    }

    public KeyboardButtonColor getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(KeyboardButtonColor buttonColor) {
        this.buttonColor = buttonColor;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<KeyboardButton> getButton() { return button; }

    private void setButton() {
        button = new ArrayList<>();
        KeyboardButton keyboardButton = new KeyboardButton();

        KeyboardButtonAction keyboardButtonAction = new KeyboardButtonAction();
        keyboardButtonAction.setType(buttonType);
        keyboardButtonAction.setLabel(label);

        keyboardButton.setAction(keyboardButtonAction);
        keyboardButton.setColor(buttonColor);

        button.add(keyboardButton);
    }
}
