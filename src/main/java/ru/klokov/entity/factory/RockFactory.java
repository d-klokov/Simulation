package ru.klokov.entity.factory;

import ru.klokov.Position;
import ru.klokov.entity.Entity;
import ru.klokov.entity.object.Rock;

public class RockFactory implements EntityFactory {
    @Override
    public Entity create(Position position) {
        return new Rock(position);
    }
}
