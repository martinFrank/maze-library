package de.elite.games.mazelib.map;


import de.elite.games.mazelib.data.TestEdgeData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TestMapEdge extends MazeMapEdge<TestEdgeData, TestMapField, TestMapEdge, TestMapPoint> {

    public TestMapEdge(TestMapPoint a, TestMapPoint b, TestEdgeData testEdgeData) {
        super(a, b, testEdgeData);
    }

    @Override
    public void draw(Object graphics) {
        GraphicsContext gc = (GraphicsContext) graphics;
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(0.5);
        TestMapPoint a = getA();
        TestMapPoint b = getB();
        double ax = a.getScaledX() + a.getPanX();
        double ay = a.getScaledY() + a.getPanY();
        double bx = b.getScaledX() + b.getPanX();
        double by = b.getScaledY() + b.getPanY();
        if (getData().getPassage().isClosed()) {
            gc.strokeLine(ax, ay, bx, by);
        }

    }

}
