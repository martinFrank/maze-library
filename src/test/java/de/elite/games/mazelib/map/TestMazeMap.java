package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.data.TestMazeMapData;

public class TestMazeMap extends MazeMap<TestMazeMapData, TestMazeMapField, TestMazeMapEdge, TestMazeMapPoint, TestMazeMapWalker> {


    TestMazeMap(int width, int height, MapStyle style) {
        super(width, height, style);
    }

    @Override
    public TestMazeMapData getData() {
        return null;
    }

    @Override
    public void setData(TestMazeMapData o) {

    }

    @Override
    public void draw(Object graphics) {
        for (TestMazeMapField field : getFields()) {
            field.draw(graphics);
        }

    }

}
