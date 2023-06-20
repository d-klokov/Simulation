package ru.klokov.entity.factory;

import ru.klokov.Position;
import ru.klokov.Simulation;
import ru.klokov.entity.Entity;
import ru.klokov.entity.creature.Herbivore;

public class HerbivoreFactory implements EntityFactory {
    private final int speed;
    private final int hp;
    private final Simulation simulation;

    public HerbivoreFactory(int speed, int hp, Simulation simulation) {
        this.speed = speed;
        this.hp = hp;
        this.simulation = simulation;
    }

    @Override
    public Entity create(Position position) {
        return new Herbivore(position, speed, hp, simulation);
    }
}
