package de.elite.games.mazelib;

import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.map.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


public class App extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private TestMazeMap testMazeMap;
    private TestMazeMapWalker testMazeMapWalker;

    private TestMazeMapField start;
    private TestMazeMapField end;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestMazeMapPartFactory mapPartFactory = new TestMazeMapPartFactory();
        TestMazeMapFactory mapFactory = new TestMazeMapFactory(mapPartFactory);
        testMazeMap = mapFactory.createMap(5, 4, MapStyle.TRIANGLE_HORIZONTAL);
        testMazeMap.scale(12f);
        testMazeMap.pan(10, 10);

        testMazeMapWalker = mapPartFactory.createWalker();

        primaryStage.setTitle("Hello World!");
        BorderPane border = new BorderPane();
        Canvas canvas = new Canvas(300, 250);

        canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            Optional<TestMazeMapPoint> point = testMazeMap.getPoint(x, y);
            Optional<TestMazeMapEdge> edge = testMazeMap.getEdge(x, y);
            Optional<TestMazeMapField> field = testMazeMap.getField(x, y);
            LOGGER.debug("x/y:{}/{} Point:{}", x, y, point);
            LOGGER.debug("x/y:{}/{} Edge:{} ", x, y, edge);
            LOGGER.debug("x/y:{}/{} Field:{}, index{} ", x, y, field, field.isPresent() ? field.get().getIndex() : "");

            if (field.isPresent()) {
                LOGGER.debug("field.getFields().size()={}", field.get().getFields().size());
                LOGGER.debug("field.getEdges().size()={}", field.get().getEdges().size());
                LOGGER.debug("field.getPoints().size()={}", field.get().getPoints().size());
            }
            edge.ifPresent(testMapField -> LOGGER.debug("edge.getFields().size()={}", testMapField.getFields().size()));

            if (point.isPresent()) {
                LOGGER.debug("point.getEdges().size={}", point.get().getEdges().size());
                LOGGER.debug("point.getFields().size={}", point.get().getFields().size());
            }

            if (mouseEvent.getButton() == MouseButton.PRIMARY && field.isPresent()) {
                start = field.get();
            }
            if (mouseEvent.getButton() == MouseButton.SECONDARY && field.isPresent()) {
                end = field.get();
            }
            if (start != null && end != null && !start.equals(end)) {
                for (TestMazeMapField any : testMazeMap.getFields()) {
                    any.getData().markAsPath(false);
                }
                List<TestMazeMapField> path = testMazeMap.aStar(start, end, testMazeMapWalker, 10);
                LOGGER.debug("Path length = {}", path.size());
                for (TestMazeMapField pathField : path) {
                    pathField.getData().markAsPath(true);
                }
                GraphicsContext gc = canvas.getGraphicsContext2D();
                drawShapes(gc);
            }
        });

        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);

        border.setCenter(canvas);
        primaryStage.setScene(new Scene(border));
        primaryStage.show();
    }


    private void drawShapes(GraphicsContext gc) {
        if (testMazeMap != null) {
            testMazeMap.draw(gc);
        }
    }
}
