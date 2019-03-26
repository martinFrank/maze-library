package de.elite.games.mazelib.map;

import de.elite.games.mazelib.data.TestPointData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TestMapPoint extends MazeMapPoint<TestPointData, TestMapField, TestMapEdge, TestMapPoint> {

    public TestMapPoint(TestPointData testPointData) {
        super(testPointData);
    }


    @Override
    public void draw(Object graphics) {
        GraphicsContext gc = (GraphicsContext) graphics;
        gc.setFill(Color.RED);
        gc.setLineWidth(3);
        double x = getPoint().getTransformed().getX();
        double y = getPoint().getTransformed().getY();
        gc.strokeLine(x, y, x, y);
    }

}

