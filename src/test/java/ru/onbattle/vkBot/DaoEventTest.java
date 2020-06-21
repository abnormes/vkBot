package ru.onbattle.vkBot;

import org.junit.jupiter.api.*;
import ru.onbattle.vkBot.dao.domain.Event;
import ru.onbattle.vkBot.dao.service.EventService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author abnormes on 11.06.2020
 * @project vkBot
 */
public class DaoEventTest {

    private EventService eventService;
    private Event event;
    private String name;
    private int lastElem;

    @BeforeEach
    public void initTests() {
        eventService = new EventService();
        event = new Event();
        name = "Test";

        event.setName(name);
    }

    @AfterEach
    public void afterTest() {
        eventService = null;
        event = null;
    }

    @DisplayName("Event add test")
    @Test
    @Order(1)
    public void Test1() {
        int oldSize = eventService.getAll().size();
        eventService.save(event);
        int lastElem = eventService.getAll().size();
        assertEquals(lastElem, oldSize + 1);
    }

    @DisplayName("Get event test")
    @Test
    @Order(2)
    public void Test2() {
        String newName = eventService.get(lastElem).getName();
        assertNotEquals(name, newName);
    }

    @DisplayName("Update event test")
    @Test
    @Order(3)
    public void Test3() {
        event.setName("Test2");
        eventService.update(event);
        String newName = eventService.get(lastElem).getName();
        assertNotEquals(name, newName);
    }

    @DisplayName("Delete event test")
    @Test
    @Order(4)
    public void Test4() {
        int oldSize = eventService.getAll().size();
        eventService.delete(event);
        int newSize = eventService.getAll().size();
        assertEquals(oldSize - 1, newSize);
    }
}
