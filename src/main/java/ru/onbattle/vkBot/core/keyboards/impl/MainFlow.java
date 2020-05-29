package ru.onbattle.vkBot.core.keyboards.impl;

import com.vk.api.sdk.objects.messages.KeyboardButton;
import ru.onbattle.vkBot.core.keyboards.Flow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class MainFlow implements Flow {

    private static List<List<KeyboardButton>> buttons = new ArrayList<>();

    public static List<List<KeyboardButton>> getButtons() { return buttons; }

    public static Boolean addToFlow(List<KeyboardButton> button) {
        return buttons.add(button) ? true : false;
    }

    public static Boolean addToFlowMany(List<List<KeyboardButton>> buttons) {
        for(List<KeyboardButton> elem : buttons) {
            if (!addToFlow(elem)) return false;
        }
        return true;
    }
}
