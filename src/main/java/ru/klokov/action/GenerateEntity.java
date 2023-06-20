package ru.klokov.action;

import ru.klokov.Map;
import ru.klokov.Position;
import ru.klokov.entity.factory.EntityFactory;

import java.util.Random;
import java.util.Set;

public class GenerateEntity implements Action {
    private final int quantity;
    private final EntityFactory factory;

    public GenerateEntity(int quantity, EntityFactory factory) {
        this.quantity = quantity;
        this.factory = factory;
    }

    @Override
    public void perform(Map map) {
        Random random = new Random();
        Set<Position> entitiesOnMapPositions = map.getEntities().keySet();

        for (int i = 0; i < quantity; i++) {
            Position position;
            do {
                position = new Position(random.nextInt(map.getWidth()), random.nextInt(map.getHeight()));
            } while (entitiesOnMapPositions.contains(position));
            map.addEntity(position, factory.create(position));
        }
    }
}
