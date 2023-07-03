package ru.klokov.entity.creature;

import ru.klokov.Color;
import ru.klokov.Position;
import ru.klokov.Simulation;
import ru.klokov.entity.Entity;
import ru.klokov.entity.object.Grass;
import ru.klokov.pathfinder.BFSPathFinder;
import ru.klokov.pathfinder.PathFinder;

import java.util.LinkedList;
import java.util.Queue;

public class Herbivore extends Creature {
    private final PathFinder pathFinder;
    private Queue<Position> pathToGrass;
    public Herbivore(Position position, int speed, int hp, Simulation simulation) {
        super(position, Color.PURPLE, speed, hp, simulation);
        this.pathFinder = new BFSPathFinder(super.getMap());
        this.pathToGrass = new LinkedList<>();
    }

    @Override
    public void makeMove() {
        if (super.getMap().creatureStillAlive(this)) {
            for (int i = 0; i < this.getSpeed(); i++) {
                Position grassPosition = pathFinder.findGoalPositionAround(Grass.class, this.getPosition());
                if (grassPosition != null) {
                    super.getMap().removeEntity(grassPosition);
                    super.getSimulation().controlGrassQuantity();
                } else {
                    if (pathToGrass.isEmpty()) {
                        pathToGrass = pathFinder.createPath(Grass.class, this.getPosition());
                    }

                    Position stepToGrass = pathToGrass.poll();
                    Entity stepToGrassEntity = super.getMap().getEntityByPosition(stepToGrass);

                    if (stepToGrassEntity instanceof Creature) {
                        pathToGrass = pathFinder.createPath(Grass.class, stepToGrass);
                        stepToGrass = pathToGrass.poll();
                    }

                    super.getMap().addEntity(stepToGrass, this);
                    super.getMap().removeEntity(this.getPosition());
                    this.setPosition(stepToGrass);
                }

                super.getSimulation().getRenderer().render(super.getMap());
            }
        }
    }
}
