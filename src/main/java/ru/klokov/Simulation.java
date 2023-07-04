package ru.klokov;

import ru.klokov.action.*;
import ru.klokov.entity.factory.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private static final int MAP_WIDTH = 20;
    private static final int MAP_HEIGHT = 10;
    private static final int MAX_GRASS_QUANTITY = 30;
    private static final int MAX_ROCKS_QUANTITY = 15;
    private static final int MAX_TREES_QUANTITY = 15;
    private static final int MAX_HERBIVORES_QUANTITY = 7;
    private static final int MAX_PREDATORS_QUANTITY = 3;
    private static final int HERBIVORE_SPEED = 1;
    private static final int HERBIVORE_HP = 4;
    private static final int PREDATOR_SPEED = 2;
    private static final int PREDATOR_HP = 10;
    private static final int PREDATOR_ATTACK_POWER = 2;
    private static final int DELAY_100 = 100;
    private static final int DELAY_250 = 250;
    private static final int DELAY_500 = 500;
    private static final int GRASS_LOAD_FACTOR = 10;
    private static final int HERBIVORE_LOAD_FACTOR = 3;
    private final Map map;
    private final ConsoleRenderer renderer;
    private int turnCounter;
    private Status status;
    private int delay;
    private final List<Action> initActions;
    private final List<Action> turnActions;

    public Simulation() {
        this.map = new Map(MAP_WIDTH, MAP_HEIGHT);
        this.renderer = new ConsoleRenderer(this);
        this.status = Status.STOPPED;
        this.delay = DELAY_250;
        this.turnCounter = 0;
        this.initActions = new ArrayList<>();
        this.turnActions = new ArrayList<>();

        init();
    }

    private void init() {
        this.initActions.add(new GenerateEntity(MAX_GRASS_QUANTITY, new GrassFactory()));
        this.initActions.add(new GenerateEntity(MAX_ROCKS_QUANTITY, new RockFactory()));
        this.initActions.add(new GenerateEntity(MAX_TREES_QUANTITY, new TreeFactory()));
        this.initActions.add(new GenerateEntity(MAX_HERBIVORES_QUANTITY,
                new HerbivoreFactory(HERBIVORE_SPEED, HERBIVORE_HP, this)));
        this.initActions.add(new GenerateEntity(MAX_PREDATORS_QUANTITY,
                new PredatorFactory(PREDATOR_SPEED, PREDATOR_HP, PREDATOR_ATTACK_POWER, this)));

        initActions.forEach(action -> action.perform(map));

        turnActions.add(new MoveCreatures());
    }

    public void nextTurn() {
        turnActions.forEach(action -> {
            action.perform(map);
            turnCounter++;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void startSimulation() {
        status = Status.RUNNING;
        renderer.render(map);
        while (!status.equals(Status.STOPPED)) {
            if (status.equals(Status.RUNNING)) {
                nextTurn();
            }
            else if (status.equals(Status.PAUSED)) {
                renderer.render(map);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        renderer.render(map);
    }

    public void pauseSimulation() {
        status = Status.PAUSED;
    }

    public void resumeSimulation() {
        status = Status.RUNNING;
    }

    public Map getMap() {
        return map;
    }
    public ConsoleRenderer getRenderer() {
        return renderer;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public Status getStatus() {
        return status;
    }

    public void stop() {
        this.status = Status.STOPPED;
    }

    public void controlGrassQuantity() {
        if (map.getGrassQuantity() == GRASS_LOAD_FACTOR) {
            new GenerateEntity(MAX_GRASS_QUANTITY - GRASS_LOAD_FACTOR,
                    new GrassFactory()).perform(map);
        }
    }

    public void controlHerbivoresQuantity() {
        if (map.getHerbivoresQuantity() == HERBIVORE_LOAD_FACTOR) {
            new GenerateEntity(MAX_HERBIVORES_QUANTITY - HERBIVORE_LOAD_FACTOR,
                    new HerbivoreFactory(HERBIVORE_SPEED, HERBIVORE_HP, this)).perform(map);
        }
    }

    public void setSpeed100() {
        this.delay = DELAY_100;
    }

    public void setSpeed250() {
        this.delay = DELAY_250;
    }

    public void setSpeed500() {
        this.delay = DELAY_500;
    }
}
