package ru.onbattle.vkBot.dao;

import java.util.Collection;

/**
 * @author abnormes on 26.05.2020
 * @project vkBot
 */
public interface Dao<Entity, Id> {
    Entity get(Id id);
    Collection<Entity> getAll();
    void save(Entity object);
    void update(Entity object);
    void delete(Entity object);
}
