package de.elite.games.mazelib.map;

import de.elite.games.geolib.GeoPoint;
import de.elite.games.maplib.MapStyle;

public class TestMapPartFactory extends MazeMapPartFactory<TestMap, TestMapField, TestMapEdge, TestMapPoint, TestMapWalker> {

    @Override
    public TestMapPoint createMapPoint(int x, int y) {
        return new TestMapPoint(x, y);
    }

    @Override
    public TestMapEdge createMapEdge(TestMapPoint a, TestMapPoint b) {
        return new TestMapEdge(a, b);
    }

    @Override
    public TestMapField createMapField(GeoPoint index) {
        return new TestMapField(index);
    }

    @Override
    public TestMap createMap(int width, int height, MapStyle style) {
        return new TestMap(width, height, style);
    }

    @Override
    public TestMapWalker createWalker() {
        return new TestMapWalker();
    }
}
