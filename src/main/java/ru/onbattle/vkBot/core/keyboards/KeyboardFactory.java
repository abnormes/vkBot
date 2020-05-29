package ru.onbattle.vkBot.core.keyboards;

import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButton;
import ru.onbattle.vkBot.core.CommandWithButton;

import java.util.List;

/**
 * @author abnormes on 29.05.2020
 * @project vkBot
 */
public class KeyboardFactory {

    private static Flow mainKeyboard = new Flow();
    private static Flow unregisteredKeyboard = new Flow();
    private static Flow registeredKeyboard = new Flow();
    private static Flow universityKeyboard = new Flow();

    public static void addToMain(List<CommandWithButton> buttons) {
        mainKeyboard.addCommands(buttons);
    }

    public static void addToUnregistered(List<CommandWithButton> buttons) {
        unregisteredKeyboard.addCommands(buttons);
    }

    public static void addToRegisteredProfile(List<CommandWithButton> buttons) {
        registeredKeyboard.addCommands(buttons);
    }

    public static void addToUniversities(List<CommandWithButton> buttons) {
        universityKeyboard.addCommands(buttons);
    }

    public static void addButtonsToUniversities(List<List<KeyboardButton>> buttons) {
        universityKeyboard.addButtons(buttons);
    }

    public static void addButtonToUniversities(List<KeyboardButton> button) {
        universityKeyboard.addButton(button);
    }

    public static Keyboard getMainKeyboard() {
        return new Keyboard().setButtons(mainKeyboard.getButtons());
    }

    public static Keyboard getUnregisteredKeyboard() {
        return new Keyboard().setButtons(unregisteredKeyboard.getButtons());
    }

    public static Keyboard getRegisteredKeyboard() {
        return new Keyboard().setButtons(registeredKeyboard.getButtons());
    }

    public static Keyboard getUniversityKeyboard() {
        return new Keyboard().setButtons(universityKeyboard.getButtons());
    }
}
