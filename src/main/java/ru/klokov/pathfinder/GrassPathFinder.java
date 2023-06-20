package ru.klokov.pathfinder;

import ru.klokov.Position;

import java.util.Queue;

public interface GrassPathFinder extends PathFinder {
    Position findGrassPositionAround(Position position);
    Queue<Position> createPathToGrass(Position position);
}
