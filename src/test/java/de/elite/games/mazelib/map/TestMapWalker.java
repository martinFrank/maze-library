package de.elite.games.mazelib.map;

public class TestMapWalker extends MazeMapWalker<TestMapField, TestMapEdge, TestMapPoint> {
    @Override
    public boolean canEnter(TestMapField from, TestMapField into) {
        return true;
    }

    @Override
    public int getEnterCosts(TestMapField from, TestMapField into) {
        return 10;
    }
}
