package ru.klokov.entity.object;

import ru.klokov.Color;
import ru.klokov.Position;
import ru.klokov.entity.Entity;

public class Grass extends Entity {
    public Grass(Position position) {
        super(position, Color.GREEN);
    }
}
