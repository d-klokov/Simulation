package ru.klokov.entity.creature;

import ru.klokov.Color;
import ru.klokov.Map;
import ru.klokov.Position;
import ru.klokov.Simulation;
import ru.klokov.entity.Entity;

public abstract class Creature extends Entity {
    private int speed;
    private int hp;
    private final Simulation simulation;
    public Creature(Position position, Color color, int speed, int hp, Simulation simulation) {
        super(position, color);
        this.speed = speed;
        this.hp = hp;
        this.simulation = simulation;
    }

    public abstract void makeMove();

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Simulation getSimulation() {
        return this.simulation;
    }

    public Map getMap() {
        return this.simulation.getMap();
    }
}
