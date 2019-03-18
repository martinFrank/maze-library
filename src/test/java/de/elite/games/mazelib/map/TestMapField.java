package de.elite.games.mazelib.map;

import de.elite.games.geolib.GeoPoint;
import de.elite.games.maplib.MapPoint;
import de.elite.games.mazelib.data.TestFieldData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TestMapField extends MazeMapField<TestFieldData, TestMapField, TestMapEdge, TestMapPoint> {


    public TestMapField(GeoPoint index, TestFieldData testFieldData) {
        super(index, testFieldData);
    }

    @Override
    public void draw(Object graphics) {
        Color color = getData().isMarkedAsPath() ? Color.YELLOW : getData().isDeadEnd() ? Color.LIGHTGRAY : Color.WHITE;
        GraphicsContext gc = (GraphicsContext) graphics;
        gc.setFill(color);
        double[] xs = getPointsOrdered().stream().mapToDouble(MapPoint::getTransformedX).toArray();
        double[] ys = getPointsOrdered().stream().mapToDouble(MapPoint::getTransformedY).toArray();
        int amount = Math.min(xs.length, ys.length);
        gc.fillPolygon(xs, ys, amount);
        for (TestMapEdge e : getEdges()) {
            e.draw(graphics);
        }
    }

}
