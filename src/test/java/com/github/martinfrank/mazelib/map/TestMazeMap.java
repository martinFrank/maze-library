package com.github.martinfrank.mazelib.map;

import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.mazelib.data.TestMazeMapData;

public class TestMazeMap extends MazeMap<TestMazeMapData, TestMazeMapField, TestMazeMapEdge, TestMazeMapNode, TestMazeMapWalker> {

    TestMazeMap(int width, int height, MapStyle style, TestMazeMapData testMapData) {
        super(width, height, style, testMapData);
    }


}
