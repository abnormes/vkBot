package ru.onbattle.vkBot;

import com.vk.api.sdk.objects.messages.Message;
import org.junit.jupiter.api.*;
import ru.onbattle.vkBot.core.VKMessenger;
import ru.onbattle.vkBot.dao.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author abnormes on 11.06.2020
 * @project vkBot
 */
public class UserInitTest {

    private Message message;
    private VKMessenger vkMessenger;
    private int fromId = 1;

    @BeforeEach
    public void init() {
        message = new Message();
        message.setFromId(fromId);
        message.setText("Test");
        message.setPeerId(151674549);

        vkMessenger = new VKMessenger(message);
    }

    @AfterEach
    public void close() {
        message = null;
        vkMessenger = null;
    }

    @DisplayName("User init test")
    @Test
    @Order(1)
    public void test1() {
        System.out.println(1);
        int size = User.getGuests().size();
        vkMessenger.run();
        int newSize = User.getGuests().size();
        assertEquals(newSize, size + 1);
    }

    @DisplayName("User excess arrays")
    @Test
    @Order(2)
    public void test2() {
        System.out.println(2);
        int size = User.getGuests().size();
        vkMessenger.run();
        assertNotEquals(size, size + 1);
    }
}
