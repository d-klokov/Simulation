package ru.klokov.pathfinder;

import ru.klokov.Map;
import ru.klokov.Position;
import ru.klokov.entity.creature.Herbivore;

import java.util.LinkedList;
import java.util.Queue;

public class BFSHerbivorePathFinder extends BFSPathFinder implements HerbivorePathFinder {
    public BFSHerbivorePathFinder(Map map) {
        super(map);
    }

    @Override
    public Position findHerbivorePositionAround(Position position) {
        for (Position current : getAvailableCellPositionsAround(position)) {
            if (super.getMap().getEntityByPosition(current) instanceof Herbivore) return current;
        }
        return null;
    }

    @Override
    public Queue<Position> createPathToHerbivore(Position position) {
        Queue<Position> positionsToVisit = new LinkedList<>(getEmptyCellPositionsAround(position));
        Queue<Position> path = new LinkedList<>();

        while (!positionsToVisit.isEmpty()) {
            Position current = positionsToVisit.poll();
            path.add(current);

            if (findHerbivorePositionAround(current) != null) break;

            for (Position step : getEmptyCellPositionsAround(current)) {
                if (!path.contains(step)) path.add(step);
            }
        }

        return path;
    }
}
