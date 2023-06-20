package ru.klokov.entity.object;

import ru.klokov.Color;
import ru.klokov.Position;
import ru.klokov.entity.Entity;

public class Rock extends Entity {
    public Rock(Position position) {
        super(position, Color.BLACK);
    }
}
