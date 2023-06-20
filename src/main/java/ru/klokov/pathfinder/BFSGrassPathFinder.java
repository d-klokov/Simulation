package ru.klokov.pathfinder;

import ru.klokov.Map;
import ru.klokov.Position;
import ru.klokov.entity.object.Grass;

import java.util.LinkedList;
import java.util.Queue;

public class BFSGrassPathFinder extends BFSPathFinder implements GrassPathFinder {
    public BFSGrassPathFinder(Map map) {
        super(map);
    }

    @Override
    public Position findGrassPositionAround(Position position) {
        for (Position current : getAvailableCellPositionsAround(position)) {
            if (super.getMap().getEntityByPosition(current) instanceof Grass) return current;
        }
        return null;
    }

    @Override
    public Queue<Position> createPathToGrass(Position position) {
        Queue<Position> positionsToVisit = new LinkedList<>(getEmptyCellPositionsAround(position));
        Queue<Position> path = new LinkedList<>();

        while (!positionsToVisit.isEmpty()) {
            Position current = positionsToVisit.poll();
            path.add(current);

            if (findGrassPositionAround(current) != null) break;

            for (Position step : getEmptyCellPositionsAround(current)) {
                if (!path.contains(step)) path.add(step);
            }
        }

        return path;
    }
}
