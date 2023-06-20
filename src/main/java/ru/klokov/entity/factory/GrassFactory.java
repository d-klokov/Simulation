package ru.klokov.entity.factory;

import ru.klokov.Position;
import ru.klokov.entity.Entity;
import ru.klokov.entity.object.Grass;

public class GrassFactory implements EntityFactory {
    @Override
    public Entity create(Position position) {
        return new Grass(position);
    }
}
