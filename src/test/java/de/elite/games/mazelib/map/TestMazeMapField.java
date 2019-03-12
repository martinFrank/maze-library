package de.elite.games.mazelib.map;

import de.elite.games.geolib.GeoPoint;
import de.elite.games.maplib.MapPoint;
import de.elite.games.mazelib.data.TestMazeMapFieldData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TestMazeMapField extends MazeMapField<TestMazeMapFieldData, TestMazeMapField, TestMazeMapEdge, TestMazeMapPoint> {

    private TestMazeMapFieldData testFieldData = new TestMazeMapFieldData();

    public TestMazeMapField(GeoPoint index) {
        super(index);
    }

    @Override
    public TestMazeMapFieldData getData() {
        return testFieldData;
    }

    @Override
    public void setData(TestMazeMapFieldData testFieldData) {
        this.testFieldData = testFieldData;

    }

    @Override
    public void draw(Object graphics) {
        Color color = getData().isMarkedAsPath() ? Color.YELLOW : Color.WHITE;
        GraphicsContext gc = (GraphicsContext) graphics;
        gc.setFill(color);
        double[] xs = getPointsOrdered().stream().mapToDouble(MapPoint::getTransformedX).toArray();
        double[] ys = getPointsOrdered().stream().mapToDouble(MapPoint::getTransformedY).toArray();
        int amount = Math.min(xs.length, ys.length);
        gc.fillPolygon(xs, ys, amount);
        for (TestMazeMapEdge e : getEdges()) {
            e.draw(graphics);
        }
    }

}
