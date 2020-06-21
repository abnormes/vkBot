package ru.onbattle.vkBot.flows;

import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButton;
import ru.onbattle.vkBot.commands.CommandWithButton;

import java.util.List;

/**
 * @author abnormes on 29.05.2020
 * @project vkBot
 */
public class LayerFactory {

    // Main layers
    private static final Layer MAIN_LAYER = new Layer();
    private static final Layer UNREGISTERED_LAYER = new Layer();
    private static final Layer REGISTERED_LAYER = new Layer();
    private static final Layer EVENT_LAYER = new Layer();
    private static final Layer STUDENT_LAYER = new Layer();

    // Sublayer's
    private static final Layer UNIVERSITY_LAYER = new Layer();
    private static final Layer NAME_LAYER = new Layer();
    private static final Layer STUDENT_REGISTRATION_LAYER = new Layer();
    private static final Layer GAME_LAYER = new Layer();
    private static final Layer TASK_LAYER = new Layer();
    private static final Layer TASK_INFO_LAYER = new Layer();

    public static void addCommandsToFlow(Layer layer,
                                         List<CommandWithButton> buttons) {
        layer.addCommands(buttons);
    }

    public static void setButtonToFlow(Layer layer,
                                       List<KeyboardButton> button) {
        layer.setButton(button);
    }

    public static void setButtonsToFlow(Layer layer,
                                        List<List<KeyboardButton>> buttons) {
        layer.setButtons(buttons);
    }

    public static void addButtonsToFlow(Layer layer,
                                        List<List<KeyboardButton>> buttons) {
        layer.addButtons(buttons);
    }

    public static void addButtonToFlow(Layer layer,
                                       List<KeyboardButton> button) {
        layer.addButton(button);
    }

    public static Layer getMainLayer() {
        return MAIN_LAYER;
    }

    public static Layer getUnregisteredLayer() {
        return UNREGISTERED_LAYER;
    }

    public static Layer getRegisteredLayer() {
        return REGISTERED_LAYER;
    }

    public static Layer getUniversityLayer() {
        return UNIVERSITY_LAYER;
    }

    public static Layer getEventLayer() {
        return EVENT_LAYER;
    }

    public static Layer getNameLayer() { return NAME_LAYER; }

    public static Layer getStudentRegistrationLayer() { return STUDENT_REGISTRATION_LAYER; }

    public static Layer getGameLayer() {
        return GAME_LAYER;
    }

    public static Layer getStudentLayer() {
        return STUDENT_LAYER;
    }

    public static Layer getTaskLayer() {
        return TASK_LAYER;
    }

    public static Layer getTaskInfoLayer() {
        return TASK_INFO_LAYER;
    }

    public static Keyboard getKeyboard(Layer layer) {
        return new Keyboard().setButtons(layer.getButtons());
    }
}
