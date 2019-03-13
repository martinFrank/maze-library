package de.elite.games.mazelib.map;

import java.util.Optional;

public class TestMapWalker extends MazeMapWalker<TestMapField, TestMapEdge, TestMapPoint> {
    @Override
    public boolean canEnter(TestMapField from, TestMapField into) {
        Optional<TestMapEdge> edge = from.getEdge(into);
        return edge.map(testMapEdge -> testMapEdge.getData().getPassage().isOpen()).orElse(false);
    }

    @Override
    public int getEnterCosts(TestMapField from, TestMapField into) {
        return 10;
    }
}
