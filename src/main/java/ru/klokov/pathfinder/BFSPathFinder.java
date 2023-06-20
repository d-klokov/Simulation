package ru.klokov.pathfinder;

import ru.klokov.Map;
import ru.klokov.Position;

import java.util.ArrayList;
import java.util.List;

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
            if (!map.getEntities().containsKey(current)) {
                emptyCellPositions.add(current);
            }
        }

        return emptyCellPositions;
    }

    public Map getMap() {
        return map;
    }
}
