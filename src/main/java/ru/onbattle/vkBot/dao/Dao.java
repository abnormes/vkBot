package ru.onbattle.vkBot.dao;

import java.util.Collection;
import java.util.Optional;

/**
 * @author abnormes on 26.05.2020
 * @project vkBot
 */
public interface Dao<Entity, Id> {
    Optional<Entity> get(int id);
    Collection<Entity> getAll();
    Optional<Id> save(Entity object);
    void update(Entity object);
    void delete(Entity object);
}
