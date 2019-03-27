package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.data.TestMazeMapData;

public class TestMazeMap extends MazeMap<TestMazeMapData, TestMazeMapField, TestMazeMapEdge, TestMazeMapNode, TestMazeMapWalker> {

    TestMazeMap(int width, int height, MapStyle style, TestMazeMapData testMapData) {
        super(width, height, style, testMapData);
    }

    @Override
    public void draw(Object graphics) {
        for (TestMazeMapField field : getFields()) {
            field.draw(graphics);
        }

    }

}
