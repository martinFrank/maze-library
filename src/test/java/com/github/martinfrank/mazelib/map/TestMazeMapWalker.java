package com.github.martinfrank.mazelib.map;

import java.util.List;

public class TestMazeMapWalker extends MazeMapWalker<TestMazeMapField, TestMazeMapEdge, TestMazeMapNode> {

    @Override
    public boolean canEnter(TestMazeMapField from, TestMazeMapField into) {
        TestMazeMapEdge edge = from.getEdge(into);
        return edge.getData().getPassage().isOpen();
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
