package ru.klokov.entity.creature;

import ru.klokov.Color;
import ru.klokov.Position;
import ru.klokov.Simulation;
import ru.klokov.entity.Entity;
import ru.klokov.pathfinder.BFSGrassPathFinder;
import ru.klokov.pathfinder.GrassPathFinder;

import java.util.LinkedList;
import java.util.Queue;

public class Herbivore extends Creature {
    private final GrassPathFinder pathFinder;
    private Queue<Position> pathToGrass;
    public Herbivore(Position position, int speed, int hp, Simulation simulation) {
        super(position, Color.PURPLE, speed, hp, simulation);
        this.pathFinder = new BFSGrassPathFinder(super.getMap());
        this.pathToGrass = new LinkedList<>();
    }

    @Override
    public void makeMove() {
        if (super.stillAlive()) {
            for (int i = 0; i < this.getSpeed(); i++) {
                Position grassPosition = pathFinder.findGrassPositionAround(this.getPosition());
                if (grassPosition != null) {
                    super.getMap().getEntities().remove(grassPosition);
                    super.getSimulation().controlGrassQuantity();
                } else {
                    if (pathToGrass.isEmpty()) {
                        pathToGrass = pathFinder.createPathToGrass(this.getPosition());
                    }

                    Position stepToGrass = pathToGrass.poll();
                    Entity stepToGrassEntity = super.getMap().getEntityByPosition(stepToGrass);

                    if (stepToGrassEntity instanceof Creature) {
                        pathToGrass = pathFinder.createPathToGrass(stepToGrass);
                        stepToGrass = pathToGrass.poll();
                    }

                    super.getMap().getEntities().put(stepToGrass, this);
                    super.getMap().getEntities().remove(this.getPosition());
                    this.setPosition(stepToGrass);
                }

                super.getSimulation().getRenderer().render(super.getMap());
            }
        }
    }
}
