package ru.klokov.entity.creature;

import ru.klokov.Color;
import ru.klokov.Position;
import ru.klokov.Simulation;

public class Herbivore extends Creature {
    public Herbivore(Position position, int speed, int hp, Simulation simulation) {
        super(position, Color.PURPLE, speed, hp, simulation);
    }

    @Override
    public void interactWithGoal(Position goalPosition) {
        super.getMap().removeEntity(goalPosition);
        super.getSimulation().controlGrassQuantity();
    }
}
