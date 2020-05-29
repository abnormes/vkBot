package ru.onbattle.vkBot.util;

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
public class AddButton {

    public static List<KeyboardButton> addButton(String label,
                                          KeyboardButtonActionType buttonType,
                                          KeyboardButtonColor buttonColor)
    {
        List<KeyboardButton> button = new ArrayList<>();
        KeyboardButton keyboardButton = new KeyboardButton();
        KeyboardButtonAction keyboardButtonAction = new KeyboardButtonAction();

        keyboardButtonAction.setType(buttonType);
        keyboardButtonAction.setLabel(label);

        keyboardButton.setAction(keyboardButtonAction);
        keyboardButton.setColor(buttonColor);

        button.add(keyboardButton);
        return button;
    }
}
