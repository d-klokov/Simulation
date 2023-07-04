package ru.klokov.entity.creature;

import ru.klokov.*;
import ru.klokov.entity.Entity;
import ru.klokov.entity.object.Grass;
import ru.klokov.pathfinder.BFSPathFinder;
import ru.klokov.pathfinder.PathFinder;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Creature extends Entity {
    private final int speed;
    private int hp;
    private final Simulation simulation;
    private final PathFinder pathFinder;
    private Queue<Position> path;
    public Creature(Position position, Color color, int speed, int hp, Simulation simulation) {
        super(position, color);
        this.speed = speed;
        this.hp = hp;
        this.simulation = simulation;
        this.pathFinder = new BFSPathFinder(getMap());
        this.path = new LinkedList<>();

    }

    public void makeMove() {
        if (getMap().creatureStillAlive(this)) {
            for (int i = 0; i < this.getSpeed(); i++) {
                Class<? extends Entity> goalClass = (this instanceof Herbivore) ? Grass.class : Herbivore.class;
                Position goalPosition = pathFinder.findGoalPositionAround(goalClass, this.getPosition());
                if (goalPosition != null) {
                    interactWithGoal(goalPosition);
                } else {
                    if (path.isEmpty()) {
                        path = pathFinder.createPath(goalClass, this.getPosition());
                    }

                    Position stepToGoal = path.poll();
                    Entity stepToGoalEntity = getMap().getEntityByPosition(stepToGoal);

                    if (stepToGoalEntity instanceof Creature) {
                        path = pathFinder.createPath(goalClass, stepToGoal);
                        stepToGoal = path.poll();
                    }

                    getMap().addEntity(stepToGoal, this);
                    getMap().removeEntity(this.getPosition());
                    this.setPosition(stepToGoal);
                }

                getRenderer().render(getMap());
            }
        }
    }

    public abstract void interactWithGoal(Position goalPosition);

    public int getSpeed() { return speed; }

    public int getHp() { return hp; }

    public void setHp(int hp) { this.hp = hp; }

    public Simulation getSimulation() { return this.simulation; }

    public Map getMap() { return this.simulation.getMap(); }

    public ConsoleRenderer getRenderer() { return this.simulation.getRenderer(); }
}
