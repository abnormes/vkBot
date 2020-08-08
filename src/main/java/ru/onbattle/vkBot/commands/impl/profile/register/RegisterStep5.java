package ru.onbattle.vkBot.commands.impl.profile.register;

import com.vk.api.sdk.objects.messages.Message;
import ru.onbattle.vkBot.commands.Command;
import ru.onbattle.vkBot.commands.State;
import ru.onbattle.vkBot.core.VKManager;
import ru.onbattle.vkBot.dao.domain.Game;
import ru.onbattle.vkBot.dao.domain.Role;
import ru.onbattle.vkBot.dao.domain.User;
import ru.onbattle.vkBot.dao.service.GameService;
import ru.onbattle.vkBot.dao.service.UserService;
import ru.onbattle.vkBot.layer.LayerFactory;

import java.util.Collection;
import java.util.Set;

/**
 * @author abnormes on 10.06.2020
 * @project vkBot
 */
public class RegisterStep5 extends Command {

    public RegisterStep5(String name, State state) {
        super(name, state);
    }

    private synchronized boolean getGames(String gameName, Game game) {
        Collection<Game> games = new GameService().getAll();

        for (Game elem : games) {
            if (gameName.equals(elem.getName())) {
                game.setId(elem.getId());
                game.setName(elem.getName());
                return true;
            }
        }
        return false;
    }

    @Override
    public void exec(Message message) {
        Runnable readingThread = () -> {
            String gameName = message.getText();
            Game game = new Game();

            boolean isGame = getGames(gameName, game);

            if (isGame) {
                User user = User.getGuestById(message.getFromId());
                user.setGame(game);
                user.setRoles(Set.of(Role.USER, Role.STUDENT));
                user.setActive(false);
                if (user.getName() != null) {
                    // DAO send to DB
                    UserService userService = new UserService();
                    if (user.isUser()) {
                        userService.update(user);
                    } else {
                        userService.save(user);
                        user.setUser(true);
                    }

                    VKManager.sendKeyboard("Ваша заявка получена на обработку",
                            message.getPeerId(),
                            LayerFactory.getKeyboard(LayerFactory.getRegisteredLayer()));
                } else {
                    VKManager.sendKeyboard("Что-то пошло не так! Попробуй снова!",
                            message.getPeerId(),
                            LayerFactory.getKeyboard(LayerFactory.getUnregisteredLayer()));
                }
                User.getGuestById(message.getFromId()).setState(State.PROFILE_REGISTERED);
            } else {
                VKManager.sendKeyboard("Прости, но я не слышал о таокй игре, попробуй снова",
                        message.getPeerId(),
                        LayerFactory.getKeyboard(LayerFactory.getUniversityLayer()));

                User.getGuestById(message.getFromId()).setState(State.REGISTER_4);
            }
        };

        new Thread(readingThread).start();
    }
}
