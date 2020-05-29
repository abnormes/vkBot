package ru.onbattle.vkBot.core.keyboards.impl;

import com.vk.api.sdk.objects.messages.KeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abnormes on 29.05.2020
 * @project vkBot
 */
public class ProfileFlow {

    private static List<List<KeyboardButton>> buttons = new ArrayList<>();

    public static List<List<KeyboardButton>> getButtons() {
        return buttons;
    }

    public static Boolean addToFlow(List<KeyboardButton> button) {
        return buttons.add(button) ? true : false;
    }

    public static Boolean addToFlowMany(List<List<KeyboardButton>> buttons) {
        for (List<KeyboardButton> elem : buttons) {
            if (!addToFlow(elem)) return false;
        }
        return true;
    }
}
