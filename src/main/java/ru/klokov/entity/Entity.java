package ru.klokov.entity;

import ru.klokov.Color;
import ru.klokov.Position;

public abstract class Entity {
    private Position position;
    private Color color;

    public Entity(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
