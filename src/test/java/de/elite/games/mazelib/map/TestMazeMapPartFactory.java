package de.elite.games.mazelib.map;

import de.elite.games.geolib.GeoPoint;
import de.elite.games.maplib.MapStyle;

public class TestMazeMapPartFactory extends MazeMapPartFactory<TestMazeMap, TestMazeMapField, TestMazeMapEdge, TestMazeMapPoint, TestMazeMapWalker> {

    @Override
    public TestMazeMapPoint createMapPoint(int x, int y) {
        return new TestMazeMapPoint(x, y);
    }

    @Override
    public TestMazeMapEdge createMapEdge(TestMazeMapPoint a, TestMazeMapPoint b) {
        return new TestMazeMapEdge(a, b);
    }

    @Override
    public TestMazeMapField createMapField(GeoPoint index) {
        return new TestMazeMapField(index);
    }

    @Override
    public TestMazeMap createMap(int width, int height, MapStyle style) {
        return new TestMazeMap(width, height, style);
    }

    @Override
    public TestMazeMapWalker createWalker() {
        return new TestMazeMapWalker();
    }
}
