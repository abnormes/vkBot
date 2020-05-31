package ru.onbattle.vkBot.core.flows;

import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButton;
import ru.onbattle.vkBot.core.CommandWithButton;

import java.util.List;

/**
 * @author abnormes on 29.05.2020
 * @project vkBot
 */
public class FlowFactory {

    private static Flow mainFlow = new Flow();
    private static Flow unregisteredFlow = new Flow();
    private static Flow registeredFlow = new Flow();
    private static Flow universityFlow = new Flow();
    private static Flow eventFlow = new Flow();
    private static Flow nameFlow = new Flow();

    public static void addCommandsToFlow(Flow flow,
                                     List<CommandWithButton> buttons) {
        flow.addCommands(buttons);
    }

    public static void setButtonToFlow(Flow flow,
                                       List<KeyboardButton> button) {
        flow.setButton(button);
    }

    public static void setButtonsToFlow(Flow flow,
                                       List<List<KeyboardButton>> buttons) {
        flow.setButtons(buttons);
    }

    public static void addButtonsToFlow(Flow flow,
                                        List<List<KeyboardButton>> buttons) {
        flow.addButtons(buttons);
    }

    public static void addButtonToFlow(Flow flow,
                                       List<KeyboardButton> button) {
        flow.addButton(button);
    }

    public static Flow getMainFlow() {
        return mainFlow;
    }

    public static Flow getUnregisteredFlow() {
        return unregisteredFlow;
    }

    public static Flow getRegisteredFlow() {
        return registeredFlow;
    }

    public static Flow getUniversityFlow() {
        return universityFlow;
    }

    public static Flow getEventFlow() {
        return eventFlow;
    }

    public static Flow getNameFlow() { return nameFlow; }

    public static Keyboard getKeyboard(Flow flow) {
        return new Keyboard().setButtons(flow.getButtons());
    }
}
