package ru.klokov.entity.creature;

import ru.klokov.Color;
import ru.klokov.Position;
import ru.klokov.Simulation;
import ru.klokov.pathfinder.BFSHerbivorePathFinder;
import ru.klokov.pathfinder.HerbivorePathFinder;

import java.util.LinkedList;
import java.util.Queue;

public class Predator extends Creature {
    private final HerbivorePathFinder pathFinder;
    private Queue<Position> pathToHerbivore;
    private final int attackPower;
    public Predator(Position position, int speed, int hp, int attackPower, Simulation simulation) {
        super(position, Color.RED, speed, hp, simulation);
        this.attackPower = attackPower;
        this.pathFinder = new BFSHerbivorePathFinder(super.getMap());
        this.pathToHerbivore = new LinkedList<>();
    }

    @Override
    public void makeMove() {
        if (super.stillAlive()) {
            for (int i = 0; i < this.getSpeed(); i++) {
                Position herbivorePosition = pathFinder.findHerbivorePositionAround(this.getPosition());

                if (herbivorePosition != null) {
                    attackHerbivore((Herbivore) this.getMap().getEntityByPosition(herbivorePosition));
                } else {
                    if (pathToHerbivore.isEmpty()) {
                        pathToHerbivore = pathFinder.createPathToHerbivore(this.getPosition());
                    }
                    Position stepToHerbivore = pathToHerbivore.poll();

                    if (super.getMap().getEntityByPosition(stepToHerbivore) instanceof Creature) {
                        pathToHerbivore = pathFinder.createPathToHerbivore(stepToHerbivore);
                        stepToHerbivore = pathToHerbivore.poll();
                    }

                    super.getMap().getEntities().put(stepToHerbivore, this);
                    super.getMap().getEntities().remove(this.getPosition());
                    this.setPosition(stepToHerbivore);
                }
            }

            super.getSimulation().getRenderer().render(super.getMap());
        }
    }

    private boolean herbivoreDeadAfterAttack(Herbivore herbivore) {
        int herbivoreHpAfterAttack = herbivore.getHp() - this.attackPower;
        herbivore.setHp(herbivoreHpAfterAttack);
        return (herbivoreHpAfterAttack <= 0);
    }

    private void attackHerbivore(Herbivore herbivore) {
        if (herbivoreDeadAfterAttack(herbivore)) {
            super.getMap().getEntities().remove(herbivore.getPosition());
            super.getSimulation().controlHerbivoresQuantity();
        }
    }
}