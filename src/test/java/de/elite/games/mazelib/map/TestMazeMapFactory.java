package de.elite.games.mazelib.map;

import com.github.martinfrank.maplib.MapFactory;
import com.github.martinfrank.maplib.MapPartFactory;

public class TestMazeMapFactory extends MapFactory<TestMazeMap, TestMazeMapField, TestMazeMapEdge, TestMazeMapNode, TestMazeMapWalker> {

    public TestMazeMapFactory(MapPartFactory<TestMazeMap, TestMazeMapField, TestMazeMapEdge, TestMazeMapNode, TestMazeMapWalker> mapPartFactory) {
        super(mapPartFactory);
    }

}
