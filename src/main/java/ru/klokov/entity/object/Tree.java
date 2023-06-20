package ru.klokov.entity.object;

import ru.klokov.Color;
import ru.klokov.Position;
import ru.klokov.entity.Entity;

public class Tree extends Entity {
    public Tree(Position position) {
        super(position, Color.CYAN);
    }
}
