package de.elite.games.mazelib.map;

import com.sun.javafx.tk.Toolkit;
import de.elite.games.drawlib.Point;
import de.elite.games.drawlib.Shape;
import de.elite.games.mazelib.data.TestMazeMapFieldData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TestMazeMapField extends MazeMapField<TestMazeMapFieldData, TestMazeMapField, TestMazeMapEdge, TestMazeMapNode> {


    public TestMazeMapField(TestMazeMapFieldData testFieldData) {
        super(testFieldData);
    }

    @Override
    public void draw(Object graphics) {
        Color color =
                getData().isMarkedAsPath() ? Color.YELLOW :
                        getData().isDeadEnd() ? Color.LIGHTGRAY :
                                getData().isBlocked() ? Color.DARKGRAY : Color.WHITE;

        GraphicsContext gc = (GraphicsContext) graphics;
        gc.setFill(color);
        Shape shape = getShape().getTransformed();
        double[] xs = shape.getPoints().stream().mapToDouble(Point::getX).toArray();
        double[] ys = shape.getPoints().stream().mapToDouble(Point::getY).toArray();
        int amount = Math.min(xs.length, ys.length);
        gc.fillPolygon(xs, ys, amount);
        for (TestMazeMapEdge e : getEdges()) {
            e.draw(graphics);
        }
        for (TestMazeMapNode n : getNodes()) {
            n.draw(graphics);
        }

        gc.setLineWidth(1);
        gc.setStroke(Color.GREEN);

        if (!getData().isBlocked()) {

            String text = "" + getData().getCounter();
            double width = Toolkit.getToolkit().getFontLoader().computeStringWidth(text, gc.getFont());
            double height = Toolkit.getToolkit().getFontLoader().getFontMetrics(gc.getFont()).getDescent();
            gc.setStroke(Color.MISTYROSE);
            gc.strokeText(text, shape.getCenter().getX() - width / 2, shape.getCenter().getY() + height);
        }
    }

}
