package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapFactory;
import de.elite.games.maplib.MapPartFactory;

public class TestMazeMapFactory extends MapFactory<TestMazeMap, TestMazeMapField, TestMazeMapEdge, TestMazeMapNode, TestMazeMapWalker> {

    public TestMazeMapFactory(MapPartFactory<TestMazeMap, TestMazeMapField, TestMazeMapEdge, TestMazeMapNode, TestMazeMapWalker> mapPartFactory) {
        super(mapPartFactory);
    }

}
