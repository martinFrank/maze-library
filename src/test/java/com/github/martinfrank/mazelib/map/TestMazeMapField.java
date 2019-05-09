package com.github.martinfrank.mazelib.map;

import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.drawlib.Shape;
import com.github.martinfrank.mazelib.TestGraphics;
import com.github.martinfrank.mazelib.data.TestMazeMapFieldData;

public class TestMazeMapField extends MazeMapField<TestMazeMapFieldData, TestMazeMapField, TestMazeMapEdge, TestMazeMapNode> {


    public TestMazeMapField(TestMazeMapFieldData testFieldData) {
        super(testFieldData);
    }

    @Override
    public void draw(Object graphics) {
        TestGraphics gc = (TestGraphics) graphics;
        Shape shape = getShape().getTransformed();

        int amount = shape.getPoints().size();
        double[] xs = new double[amount];
        double[] ys = new double[amount];

        for (int i = 0; i < amount; i++) {
            Point point = shape.getPoints().get(i);
            xs[i] = point.getX();
            ys[i] = point.getY();
        }

        gc.drawPolygon(xs, ys, amount);
        for (TestMazeMapEdge e : getEdges()) {
            e.draw(graphics);
        }
        for (TestMazeMapNode n : getNodes()) {
            n.draw(graphics);
        }

    }

}
