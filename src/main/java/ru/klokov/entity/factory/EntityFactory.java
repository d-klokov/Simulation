package ru.klokov.entity.factory;

import ru.klokov.Position;
import ru.klokov.entity.Entity;

public interface EntityFactory {
    Entity create(Position position);
}
