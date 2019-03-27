package de.elite.games.mazelib.map;


import de.elite.games.drawlib.Point;
import de.elite.games.mazelib.data.TestMazeMapEdgeData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TestMazeMapEdge extends MazeMapEdge<TestMazeMapEdgeData, TestMazeMapField, TestMazeMapEdge, TestMazeMapNode> {

    public TestMazeMapEdge(TestMazeMapEdgeData testEdgeData) {
        super(testEdgeData);
    }

    @Override
    public void draw(Object graphics) {
        GraphicsContext gc = (GraphicsContext) graphics;
//        gc.setStroke(getData().getPassage().isClosed() ? Color.BLACK : Color.LIGHTGRAY);
        gc.setStroke(getData().getPassage().isClosed() ? Color.DARKGRAY.darker() : Color.LIGHTGRAY);
        gc.setLineWidth(getData().getPassage().isClosed() ? 2 : 1);
        Point a = getLine().getA().getTransformed();
        Point b = getLine().getB().getTransformed();
        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());

    }

}
