package ru.klokov.entity.factory;

import ru.klokov.Position;
import ru.klokov.Simulation;
import ru.klokov.entity.Entity;
import ru.klokov.entity.creature.Predator;

public class PredatorFactory implements EntityFactory {
    private final int speed;
    private final int hp;
    private final int attackPower;
    private final Simulation simulation;

    public PredatorFactory(int speed, int hp, int attackPower, Simulation simulation) {
        this.speed = speed;
        this.hp = hp;
        this.attackPower = attackPower;
        this.simulation = simulation;
    }

    @Override
    public Entity create(Position position) {
        return new Predator(position, speed, hp, attackPower, simulation);
    }
}
