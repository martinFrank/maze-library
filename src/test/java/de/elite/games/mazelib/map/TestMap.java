package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.data.TestMapData;

public class TestMap extends MazeMap<TestMapData, TestMapField, TestMapEdge, TestMapNode, TestMapWalker> {

    TestMap(int width, int height, MapStyle style, TestMapData testMapData) {
        super(width, height, style, testMapData);
    }

    @Override
    public void draw(Object graphics) {
        for (TestMapField field : getFields()) {
            field.draw(graphics);
        }

    }

}
