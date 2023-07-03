package ru.klokov;

import ru.klokov.entity.Entity;
import ru.klokov.entity.creature.Creature;
import ru.klokov.entity.creature.Herbivore;
import ru.klokov.entity.object.Grass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Map {
    private final int width;
    private final int height;
    private final java.util.Map<Position, Entity> entities;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new HashMap<>();
    }

    public void addEntity(Position position, Entity entity) {
        this.entities.put(position, entity);
    }
    public void removeEntity(Position position) { this.entities.remove(position); }
    public Entity getEntityByPosition(Position position) {
        return this.entities.get(position);
    }
    public boolean creatureStillAlive(Creature creature) { return this.entities.containsValue(creature); }
    public boolean cellIsEmpty(Position position) { return !this.entities.containsKey(position); }
    public Set<Position> getEntitiesPositions() { return this.entities.keySet(); }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Creature> getCreatures() {
        List<Creature> creatures = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (entity instanceof Creature) creatures.add((Creature) entity);
        }
        return creatures;
    }

    public <T> List<T> getEntitiesByType(Class<T> type) {
        List<T> entities = new ArrayList<>();
        for (Entity entity : this.entities.values()) {
            if (type.isInstance(entity)) entities.add(type.cast(entity));
        }
        return entities;
    }

    public int getGrassQuantity() {
        return getEntitiesByType(Grass.class).size();
    }

    public int getHerbivoresQuantity() {
        return getEntitiesByType(Herbivore.class).size();
    }
}
