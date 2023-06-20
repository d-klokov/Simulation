package ru.klokov;

import ru.klokov.entity.Entity;
import ru.klokov.entity.creature.Creature;
import ru.klokov.entity.creature.Herbivore;
import ru.klokov.entity.object.Grass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public Entity getEntityByPosition(Position position) {
        return entities.get(position);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public java.util.Map<Position, Entity> getEntities() {
        return entities;
    }
    public List<Creature> getCreatures() {
        List<Creature> creatures = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (entity instanceof Creature) creatures.add((Creature) entity);
        }
        return creatures;
    }

    public int getGrassQuantity() {
        List<Grass> grassList = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (entity instanceof Grass) grassList.add((Grass) entity);
        }
        return grassList.size();
    }

    public int getHerbivoresQuantity() {
        List<Herbivore> herbivores = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (entity instanceof Herbivore) herbivores.add((Herbivore) entity);
        }
        return herbivores.size();
    }
}
