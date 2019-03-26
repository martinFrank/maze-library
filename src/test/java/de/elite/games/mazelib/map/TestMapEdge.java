package de.elite.games.mazelib.map;


import de.elite.games.drawlib.Point;
import de.elite.games.mazelib.data.TestEdgeData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TestMapEdge extends MazeMapEdge<TestEdgeData, TestMapField, TestMapEdge, TestMapPoint> {

    public TestMapEdge(TestEdgeData testEdgeData) {
        super(testEdgeData);
    }

    @Override
    public void draw(Object graphics) {
        GraphicsContext gc = (GraphicsContext) graphics;
        gc.setStroke(getData().getPassage().isClosed() ? Color.BLACK : Color.LIGHTGRAY);
        gc.setLineWidth(getData().getPassage().isClosed() ? 2 : 1);
        Point a = getLine().getA().getTransformed();
        Point b = getLine().getB().getTransformed();
        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());

    }

}
