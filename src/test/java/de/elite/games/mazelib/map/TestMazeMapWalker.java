package de.elite.games.mazelib.map;

import java.util.List;
import java.util.Optional;

public class TestMazeMapWalker extends MazeMapWalker<TestMazeMapField, TestMazeMapEdge, TestMazeMapNode> {

    @Override
    public boolean canEnter(TestMazeMapField from, TestMazeMapField into) {
        Optional<TestMazeMapEdge> edge = from.getEdge(into);
        return edge.map(testMapEdge -> testMapEdge.getData().getPassage().isOpen()).orElse(false);
    }

    @Override
    public int getEnterCosts(TestMazeMapField from, TestMazeMapField into) {
        return 10;
    }

    @Override
    public List<TestMazeMapField> getNeighbours(TestMazeMapField field) {
        return getNeighboursFromEdges(field);
    }
}
