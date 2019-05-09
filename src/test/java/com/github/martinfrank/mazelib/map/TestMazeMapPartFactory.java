package com.github.martinfrank.mazelib.map;

import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.mazelib.data.TestMazeMapData;
import com.github.martinfrank.mazelib.data.TestMazeMapEdgeData;
import com.github.martinfrank.mazelib.data.TestMazeMapFieldData;
import com.github.martinfrank.mazelib.data.TestMazeMapNodeData;

public class TestMazeMapPartFactory extends MazeMapPartFactory<TestMazeMap, TestMazeMapField, TestMazeMapEdge, TestMazeMapNode, TestMazeMapWalker> {

    @Override
    public TestMazeMapNode createMapNode() {
        return new TestMazeMapNode(new TestMazeMapNodeData());
    }

    @Override
    public TestMazeMapEdge createMapEdge() {
        return new TestMazeMapEdge(new TestMazeMapEdgeData());
    }

    @Override
    public TestMazeMapField createMapField() {
        return new TestMazeMapField(new TestMazeMapFieldData());
    }

    @Override
    public TestMazeMap createMap(int columns, int rows, MapStyle style) {
        return new TestMazeMap(columns, rows, style, new TestMazeMapData());
    }

    @Override
    public TestMazeMapWalker createWalker() {
        return new TestMazeMapWalker();
    }
}
