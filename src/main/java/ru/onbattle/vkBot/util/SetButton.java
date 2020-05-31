package ru.onbattle.vkBot.util;

import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.KeyboardButtonAction;
import com.vk.api.sdk.objects.messages.KeyboardButtonActionType;
import com.vk.api.sdk.objects.messages.KeyboardButtonColor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abnormes on 29.05.2020
 * @project vkBot
 */
public class SetButton {

    public static List<KeyboardButton> setButton(String label) {
        List<KeyboardButton> button = new ArrayList<>();
        CommonAdd(button, label);
        return button;
    }

    public static List<List<KeyboardButton>> setButton(List<String> list, int dimensionSize) {

        List<List<KeyboardButton>> buttonsList = new ArrayList<>();

        for (int i = 0; i < list.size(); i+=dimensionSize) {
            List<KeyboardButton> buttons = new ArrayList<>(dimensionSize);
            for (int j = 0; j < dimensionSize && (i + j) < list.size(); j++) {
                CommonAdd(buttons, list.get(i + j));
            }
            buttonsList.add(buttons);
        }
        return buttonsList;
    }

    private static void CommonAdd(List<KeyboardButton> button, String item) {
        KeyboardButton keyboardButton = new KeyboardButton();

        KeyboardButtonAction keyboardButtonAction = new KeyboardButtonAction();
        keyboardButtonAction.setType(KeyboardButtonActionType.TEXT);
        keyboardButtonAction.setLabel(item);

        keyboardButton.setAction(keyboardButtonAction);
        keyboardButton.setColor(KeyboardButtonColor.DEFAULT);

        button.add(keyboardButton);
    }
}
