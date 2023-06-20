package ru.klokov.pathfinder;

import ru.klokov.Position;

import java.util.Queue;

public interface HerbivorePathFinder extends PathFinder {
    Position findHerbivorePositionAround(Position position);
    Queue<Position> createPathToHerbivore(Position position);
}
