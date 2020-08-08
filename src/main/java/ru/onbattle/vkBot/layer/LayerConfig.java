package ru.onbattle.vkBot.layer;

import ru.onbattle.vkBot.layer.sublayer.Sublayer;
import ru.onbattle.vkBot.layer.sublayer.impl.*;

import java.util.List;

/**
 * Configurer for flows
 * @author abnormes on 31.05.2020
 * @project vkBot
 */
public class LayerConfig {

    private Thread eventThread;
    private Thread universityThread;
    private Thread gameThread;

    private final Sublayer eventSublayer = new EventSublayer();
    private final Sublayer universitySublayer = new UniversitySublayer();
    private final Sublayer gameSublayer = new GameRegisterSublayer();

    private final Sublayer nameSublayer = new NameSublayer();
    private final Sublayer studentSublayer = new StudentSublayer();
    private final Sublayer taskInfoSublayer = new TaskInfoSublayer();

    public LayerConfig() {
        eventThread = new Thread(eventSublayer);
        universityThread = new Thread(universitySublayer);
        gameThread = new Thread(gameSublayer);

        eventThread.start();
        universityThread.start();
        gameThread.start();
    }

    public synchronized void init(Layer layer, List commands) {
        try {
            eventThread.join();
            universityThread.join();
            gameThread.join();
            LayerFactory.addCommandsToFlow(layer, commands);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
