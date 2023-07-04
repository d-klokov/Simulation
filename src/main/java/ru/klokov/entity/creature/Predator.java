package ru.klokov.entity.creature;

import ru.klokov.Color;
import ru.klokov.Position;
import ru.klokov.Simulation;

public class Predator extends Creature {
    private final int attackPower;
    public Predator(Position position, int speed, int hp, int attackPower, Simulation simulation) {
        super(position, Color.RED, speed, hp, simulation);
        this.attackPower = attackPower;
    }

    @Override
    public void interactWithGoal(Position goalPosition) {
        attackHerbivore((Herbivore) super.getMap().getEntityByPosition(goalPosition));
    }

    private boolean herbivoreDeadAfterAttack(Herbivore herbivore) {
        int herbivoreHpAfterAttack = herbivore.getHp() - attackPower;
        herbivore.setHp(herbivoreHpAfterAttack);
        return (herbivoreHpAfterAttack <= 0);
    }

    private void attackHerbivore(Herbivore herbivore) {
        if (herbivoreDeadAfterAttack(herbivore)) {
            super.getMap().removeEntity(herbivore.getPosition());
            super.getSimulation().controlHerbivoresQuantity();
        }
    }
}
