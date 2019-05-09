package com.github.martinfrank.mazelib.map;


import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.mazelib.TestGraphics;
import com.github.martinfrank.mazelib.data.TestMazeMapEdgeData;

public class TestMazeMapEdge extends MazeMapEdge<TestMazeMapEdgeData, TestMazeMapField, TestMazeMapEdge, TestMazeMapNode> {

    public TestMazeMapEdge(TestMazeMapEdgeData testEdgeData) {
        super(testEdgeData);
    }

    @Override
    public void draw(Object graphics) {
        TestGraphics gc = (TestGraphics) graphics;
        Point a = getLine().getA().getTransformed();
        Point b = getLine().getB().getTransformed();
        gc.drawLine(a.getX(), a.getY(), b.getX(), b.getY());

    }

}
