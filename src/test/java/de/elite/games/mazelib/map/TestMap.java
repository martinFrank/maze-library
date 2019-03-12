package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.data.TestMapData;

public class TestMap extends MazeMap<TestMapData, TestMapField, TestMapEdge, TestMapPoint, TestMapWalker> {


    TestMap(int width, int height, MapStyle style) {
        super(width, height, style);
    }

    @Override
    public TestMapData getData() {
        return null;
    }

    @Override
    public void setData(TestMapData o) {

    }

    @Override
    public void draw(Object graphics) {
        for (TestMapField field : getFields()) {
            field.draw(graphics);
        }

    }

}
