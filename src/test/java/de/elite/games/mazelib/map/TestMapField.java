package de.elite.games.mazelib.map;

import de.elite.games.drawlib.Point;
import de.elite.games.drawlib.Shape;
import de.elite.games.mazelib.data.TestFieldData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TestMapField extends MazeMapField<TestFieldData, TestMapField, TestMapEdge, TestMapNode> {


    public TestMapField(TestFieldData testFieldData) {
        super(testFieldData);
    }

    @Override
    public void draw(Object graphics) {
        Color color = getData().isMarkedAsPath() ? Color.YELLOW : getData().isDeadEnd() ? Color.LIGHTGRAY : Color.WHITE;
        GraphicsContext gc = (GraphicsContext) graphics;
        gc.setFill(color);
        Shape shape = getShape().getTransformed();
        double[] xs = shape.getPoints().stream().mapToDouble(Point::getX).toArray();
        double[] ys = shape.getPoints().stream().mapToDouble(Point::getY).toArray();
        int amount = Math.min(xs.length, ys.length);
        gc.fillPolygon(xs, ys, amount);
        for (TestMapEdge e : getEdges()) {
            e.draw(graphics);
        }
        for (TestMapNode n : getNodes()) {
            n.draw(graphics);
        }
    }

}
