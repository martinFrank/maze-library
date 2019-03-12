package de.elite.games.mazelib.map;

import de.elite.games.mazelib.data.TestMazeMapPointData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TestMazeMapPoint extends MazeMapPoint<TestMazeMapPointData, TestMazeMapField, TestMazeMapEdge, TestMazeMapPoint> {

    public TestMazeMapPoint(int x, int y) {
        super(x, y);
    }

    @Override
    public TestMazeMapPointData getData() {
        return null;
    }

    @Override
    public void setData(TestMazeMapPointData testPointData) {

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

