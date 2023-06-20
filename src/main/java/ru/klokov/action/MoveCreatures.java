package ru.klokov.action;

import ru.klokov.Map;
import ru.klokov.entity.creature.Creature;

public class MoveCreatures implements Action {
    @Override
    public void perform(Map map) {
        map.getCreatures().forEach(Creature::makeMove);
    }
}
