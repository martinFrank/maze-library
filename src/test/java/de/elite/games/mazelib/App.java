package de.elite.games.mazelib;

import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.MazeGenerationParams.AlgorithmType;
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
    private TestMazeMap demoMap;

    private TestMazeMapField start;
    private TestMazeMapField end;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TestMazeMapPartFactory mapPartFactory = new TestMazeMapPartFactory();
        TestMazeMapFactory mapFactory = new TestMazeMapFactory(mapPartFactory);
//        demoMap = mapFactory.createMap(8, 6, MapStyle.TRIANGLE_HORIZONTAL);
//        demoMap = mapFactory.createMap(8, 6, MapStyle.TRIANGLE_VERTICAL);
//        demoMap = mapFactory.createMap(21, 21, MapStyle.SQUARE_ISOMETRIC);
//        demoMap = mapFactory.createMap(14, 24, MapStyle.SQUARE_ISOMETRIC);
//        demoMap = mapFactory.createMap(21, 11, MapStyle.HEX_HORIZONTAL);
        demoMap = mapFactory.createMap(14, 15, MapStyle.HEX_VERTICAL);
//        demoMap = mapFactory.createMap(13, 13, MapStyle.SQUARE);
        demoMap.scale(11f);

        LOGGER.debug("map columns/rows {}/{}", demoMap.getColumns(), demoMap.getRows());

        MazeGenerationParams params = new MazeGenerationParams(AlgorithmType.RECURSIVE_BACKTRACKER_PASSAGES);
//        MazeGenerationParams params = new MazeGenerationParams(AlgorithmType.RECURSIVE_BACKTRACKER_BLOCKS);
        demoMap.generateMaze(params);

        TestMazeMapWalker walker = mapPartFactory.createWalker();

        primaryStage.setTitle("Maze Demo");
        BorderPane border = new BorderPane();
        Canvas canvas = new Canvas(demoMap.getTransformed().getWidth(), demoMap.getTransformed().getHeight());

        canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
//            Optional<TestMazeMapNode> point = demoMap.getNodeAt(x, y);
//            Optional<TestMazeMapEdge> edge = demoMap.getEdgeAt(x, y);
            Optional<TestMazeMapField> field = demoMap.getFieldAt(x, y);
//            LOGGER.debug("x/y:{}/{} Point:{}", x, y, point);
//            LOGGER.debug("x/y:{}/{} Edge:{}", x, y, edge);
//            LOGGER.debug("x/y:{}/{} Field:{}", x, y, field);

//            if (field.isPresent()) {
//                LOGGER.debug("field.getFields().size()={}", field.get().getFields().size());
//                LOGGER.debug("field.getEdges().size()={}", field.get().getEdges().size());
//                LOGGER.debug("field.getPoints().size()={}", field.get().getNodes().size());
//            }
//            edge.ifPresent(testMapField -> LOGGER.debug("edge.getFields().size()={}", testMapField.getFields().size()));
//
//            if (point.isPresent()) {
//                LOGGER.debug("point.getEdges().size={}", point.get().getEdges().size());
//                LOGGER.debug("point.getFields().size={}", point.get().getFields().size());
//            }

            if (mouseEvent.getButton() == MouseButton.PRIMARY && field.isPresent()) {
                start = field.get();
            }
            if (mouseEvent.getButton() == MouseButton.SECONDARY && field.isPresent()) {
                end = field.get();
            }
            if (start != null && end != null && !start.equals(end)) {
                LOGGER.debug("start {}", start.getIndex());
                LOGGER.debug("end   {}", end.getIndex());

                for (TestMazeMapField any : demoMap.getFields()) {
                    any.getData().markAsPath(false);
                }
                List<TestMazeMapField> path = demoMap.aStar(start, end, walker, 1000);
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
        if (demoMap != null) {
            demoMap.draw(gc);
        }
    }
}
