package ru.onbattle.vkBot.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author abnormes on 24.05.2020
 * @project vkBot
 */
public class GetRandomId {

    private static Integer randId;

    public static Integer getRandomId() { return ThreadLocalRandom.current().nextInt(); }
}
