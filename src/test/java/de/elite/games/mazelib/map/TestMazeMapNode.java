package de.elite.games.mazelib.map;

import de.elite.games.mazelib.data.TestMazeMapNodeData;

public class TestMazeMapNode extends MazeMapNode<TestMazeMapNodeData, TestMazeMapField, TestMazeMapEdge, TestMazeMapNode> {

    public TestMazeMapNode(TestMazeMapNodeData testPointData) {
        super(testPointData);
    }


    @Override
    public void draw(Object graphics) {
//        GraphicsContext gc = (GraphicsContext) graphics;
//        gc.setStroke(Color.DARKCYAN);
//        gc.setLineWidth(3);
//        double x = getPoint().getTransformed().getX();
//        double y = getPoint().getTransformed().getY();
//        gc.strokeLine(x, y, x, y);
    }

}

