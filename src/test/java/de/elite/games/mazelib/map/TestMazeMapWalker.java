package de.elite.games.mazelib.map;

public class TestMazeMapWalker extends MazeMapWalker<TestMazeMapField, TestMazeMapEdge, TestMazeMapPoint> {
    @Override
    public boolean canEnter(TestMazeMapField from, TestMazeMapField into) {
        return true;
    }

    @Override
    public int getEnterCosts(TestMazeMapField from, TestMazeMapField into) {
        return 10;
    }
}
