package ru.klokov.pathfinder;

import ru.klokov.Map;
import ru.klokov.Position;
import ru.klokov.entity.Entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSPathFinder implements PathFinder {
    private final Map map;

    public BFSPathFinder(Map map) {
        this.map = map;
    }

    @Override
    public List<Position> getAvailableCellPositionsAround(Position position) {
        List<Position> availablePositions = new ArrayList<>();

        if (position != null) {
            int x = position.getX();
            int y = position.getY();

            // left up
            if (x - 1 >= 0 && y - 1 >= 0) {
                availablePositions.add(new Position(x - 1, y - 1));
            }
            // up
            if (y - 1 >= 0) {
                availablePositions.add(new Position(x, y - 1));
            }
            // right up
            if (x + 1 < map.getWidth() && y - 1 >= 0) {
                availablePositions.add(new Position(x + 1, y - 1));
            }
            // right
            if (x + 1 < map.getWidth()) {
                availablePositions.add(new Position(x + 1, y));
            }
            // right down
            if (x + 1 < map.getWidth() && y + 1 < map.getHeight()) {
                availablePositions.add(new Position(x + 1, y + 1));
            }
            // down
            if (y + 1 < map.getHeight()) {
                availablePositions.add(new Position(x, y + 1));
            }
            // left down
            if (x - 1 >= 0 && y + 1 < map.getHeight()) {
                availablePositions.add(new Position(x - 1, y + 1));
            }
            // left
            if (x - 1 >= 0) {
                availablePositions.add(new Position(x - 1, y));
            }
        }

        return availablePositions;
    }

    @Override
    public List<Position> getEmptyCellPositionsAround(Position position) {
        List<Position> emptyCellPositions = new ArrayList<>();

        for (Position current : getAvailableCellPositionsAround(position)) {
            if (map.cellIsEmpty(current)) {
                emptyCellPositions.add(current);
            }
        }

        return emptyCellPositions;
    }

    @Override
    public Position findGoalPositionAround(Class<? extends Entity> goalClass, Position position) {
        for (Position current : getAvailableCellPositionsAround(position)) {
            if (goalClass.isInstance(map.getEntityByPosition(current))) return current;
        }
        return null;
    }

    @Override
    public Queue<Position> createPath(Class<? extends Entity> goalClass, Position position) {
        Queue<Position> positionsToVisit = new LinkedList<>(getEmptyCellPositionsAround(position));
        Queue<Position> path = new LinkedList<>();

        while (!positionsToVisit.isEmpty()) {
            Position current = positionsToVisit.poll();
            path.add(current);

            if (findGoalPositionAround(goalClass, current) != null) break;

            for (Position step : getEmptyCellPositionsAround(current)) {
                if (!path.contains(step)) path.add(step);
            }
        }

        return path;
    }
}
