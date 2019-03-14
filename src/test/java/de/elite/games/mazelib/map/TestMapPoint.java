package de.elite.games.mazelib.map;

import de.elite.games.mazelib.data.TestPointData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TestMapPoint extends MazeMapPoint<TestPointData, TestMapField, TestMapEdge, TestMapPoint> {

    public TestMapPoint(int x, int y, TestPointData testPointData) {
        super(x, y, testPointData);
    }


    @Override
    public void draw(Object graphics) {
        GraphicsContext gc = (GraphicsContext) graphics;
        gc.setFill(Color.RED);
        gc.setLineWidth(3);
        double x = getTransformedX();
        double y = getTransformedY();
        gc.strokeLine(x, y, x, y);
    }

}

