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
    private TestMap demoMap;
    private TestMapWalker walker;

    private TestMapField start;
    private TestMapField end;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestMapPartFactory mapPartFactory = new TestMapPartFactory();
        TestMapFactory mapFactory = new TestMapFactory(mapPartFactory);
//        demoMap = mapFactory.createMap(8, 6, MapStyle.TRIANGLE_HORIZONTAL);
        demoMap = mapFactory.createMap(8, 6, MapStyle.TRIANGLE_VERTICAL);
//        demoMap = mapFactory.createMap(8, 6, MapStyle.HEX_VERTICAL);
//        demoMap = mapFactory.createMap(3, 3, MapStyle.SQUARE8);
        demoMap.scale(12f);
        demoMap.pan(10, 10);

        MazeGenerationParams params = new MazeGenerationParams();

        demoMap.generateMaze(params);

        walker = mapPartFactory.createWalker();

        primaryStage.setTitle("Hello World!");
        BorderPane border = new BorderPane();
        Canvas canvas = new Canvas(450, 250);

        canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            Optional<TestMapPoint> point = demoMap.getPoint(x, y);
            Optional<TestMapEdge> edge = demoMap.getEdge(x, y);
            Optional<TestMapField> field = demoMap.getField(x, y);
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
                for (TestMapField any : demoMap.getFields()) {
                    any.getData().markAsPath(false);
                }
                List<TestMapField> path = demoMap.aStar(start, end, walker, 100);
                LOGGER.debug("Path length = {}", path.size());
                for (TestMapField pathField : path) {
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
        if (demoMap != null) {
            demoMap.draw(gc);
        }
    }
}
