package de.elite.games.mazelib.map;


import de.elite.games.mazelib.data.TestMazeMapEdgeData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TestMazeMapEdge extends MazeMapEdge<TestMazeMapEdgeData, TestMazeMapField, TestMazeMapEdge, TestMazeMapPoint> {

    public TestMazeMapEdge(TestMazeMapPoint a, TestMazeMapPoint b) {
        super(a, b);
    }

    @Override
    public TestMazeMapEdgeData getData() {
        return null;
    }

    @Override
    public void setData(TestMazeMapEdgeData testEdgeData) {

    }

    @Override
    public void draw(Object graphics) {
        GraphicsContext gc = (GraphicsContext) graphics;
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(0.5);
        TestMazeMapPoint a = getA();
        TestMazeMapPoint b = getB();
        double ax = a.getScaledX() + a.getPanX();
        double ay = a.getScaledY() + a.getPanY();
        double bx = b.getScaledX() + b.getPanX();
        double by = b.getScaledY() + b.getPanY();
        gc.strokeLine(ax, ay, bx, by);

    }

}
