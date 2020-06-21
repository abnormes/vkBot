package ru.onbattle.vkBot;


import org.junit.jupiter.api.*;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.dao.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * @author abnormes on 10.06.2020
 * @project vkBot
 */
public class DaoUserTest {

    private UserService userService;
    private User user;
    private String name;
    private int id;

    @BeforeEach
    public void initTests() {
        userService = new UserService();
        user = new User();
        name = "Test";
        id = 1;

        user.setId(id);
        user.setName(name);
    }

    @AfterEach
    public void afterTest() {
        userService = null;
        user = null;
    }

    @DisplayName("User add test")
    @Test
    @Order(1)
    public void addUser() {
        int oldSize = userService.getAll().size();
        userService.save(user);
        int newSize = userService.getAll().size();
        assertEquals(newSize, oldSize + 1);
    }

    @DisplayName("Get user test")
    @Test
    @Order(2)
    public void getUser() {
        String newName = userService.get(1).getName();
        assertNotEquals(name, newName);
    }

    @DisplayName("Update user test")
    @Test
    @Order(3)
    public void updateUser() {
        user.setName("Test2");
        userService.update(user);
        String newName = userService.get(user.getId()).getName();
        assertNotEquals(name, newName);
    }

    @DisplayName("Delete user test")
    @Test
    @Order(4)
    public void deleteUser() {
        int oldSize = userService.getAll().size();
        userService.delete(user);
        int newSize = userService.getAll().size();
        assertEquals(oldSize - 1, newSize);
    }
}
