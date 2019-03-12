package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapFactory;
import de.elite.games.maplib.MapPartFactory;

public class TestMazeMapFactory extends MapFactory<TestMazeMap, TestMazeMapField, TestMazeMapEdge, TestMazeMapPoint, TestMazeMapWalker> {

    public TestMazeMapFactory(MapPartFactory<TestMazeMap, TestMazeMapField, TestMazeMapEdge, TestMazeMapPoint, TestMazeMapWalker> mapPartFactory) {
        super(mapPartFactory);
    }

}
