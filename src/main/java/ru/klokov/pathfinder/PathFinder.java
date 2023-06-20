package ru.klokov.pathfinder;

import ru.klokov.Position;

import java.util.List;

public interface PathFinder {
    List<Position> getAvailableCellPositionsAround(Position position);
    List<Position> getEmptyCellPositionsAround(Position position);
}
