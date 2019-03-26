package de.elite.games.mazelib.map;

import de.elite.games.mazelib.data.TestNodeData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TestMapNode extends MazeMapPoint<TestNodeData, TestMapField, TestMapEdge, TestMapNode> {

    public TestMapNode(TestNodeData testPointData) {
        super(testPointData);
    }


    @Override
    public void draw(Object graphics) {
        GraphicsContext gc = (GraphicsContext) graphics;
        gc.setStroke(Color.DARKCYAN);
        gc.setLineWidth(3);
        double x = getPoint().getTransformed().getX();
        double y = getPoint().getTransformed().getY();
        gc.strokeLine(x, y, x, y);
    }

}

