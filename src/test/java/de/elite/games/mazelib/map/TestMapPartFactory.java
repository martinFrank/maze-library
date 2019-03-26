package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.data.TestEdgeData;
import de.elite.games.mazelib.data.TestFieldData;
import de.elite.games.mazelib.data.TestMapData;
import de.elite.games.mazelib.data.TestNodeData;

public class TestMapPartFactory extends MazeMapPartFactory<TestMap, TestMapField, TestMapEdge, TestMapNode, TestMapWalker> {

    @Override
    public TestMapNode createMapNode() {
        return new TestMapNode(new TestNodeData());
    }

    @Override
    public TestMapEdge createMapEdge() {
        return new TestMapEdge(new TestEdgeData());
    }

    @Override
    public TestMapField createMapField() {
        return new TestMapField(new TestFieldData());
    }

    @Override
    public TestMap createMap(int width, int height, MapStyle style) {
        return new TestMap(width, height, style, new TestMapData());
    }

    @Override
    public TestMapWalker createWalker() {
        return new TestMapWalker();
    }
}
