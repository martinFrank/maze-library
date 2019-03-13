package de.elite.games.mazelib.map;

import de.elite.games.geolib.GeoPoint;
import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.data.TestEdgeData;
import de.elite.games.mazelib.data.TestFieldData;
import de.elite.games.mazelib.data.TestMapData;
import de.elite.games.mazelib.data.TestPointData;

public class TestMapPartFactory extends MazeMapPartFactory<TestMap, TestMapField, TestMapEdge, TestMapPoint, TestMapWalker> {

    @Override
    public TestMapPoint createMapPoint(int x, int y) {
        return new TestMapPoint(x, y, new TestPointData());
    }

    @Override
    public TestMapEdge createMapEdge(TestMapPoint a, TestMapPoint b) {
        return new TestMapEdge(a, b, new TestEdgeData());
    }

    @Override
    public TestMapField createMapField(GeoPoint index) {
        return new TestMapField(index, new TestFieldData());
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
