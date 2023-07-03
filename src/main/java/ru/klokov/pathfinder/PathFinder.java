package ru.klokov.pathfinder;

import ru.klokov.Position;
import ru.klokov.entity.Entity;

import java.util.List;
import java.util.Queue;

public interface PathFinder {
    List<Position> getAvailableCellPositionsAround(Position position);
    List<Position> getEmptyCellPositionsAround(Position position);
    Position findGoalPositionAround(Class<? extends Entity> goalClass, Position position);
    Queue<Position> createPath(Class<? extends Entity> goalClass, Position position);
}
