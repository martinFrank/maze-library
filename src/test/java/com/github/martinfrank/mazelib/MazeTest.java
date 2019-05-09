package com.github.martinfrank.mazelib;

import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.mazelib.map.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeTest {

    private final TestMazeMapPartFactory mapPartFactory = new TestMazeMapPartFactory();
    private final TestMazeMapFactory mapFactory = new TestMazeMapFactory(mapPartFactory);

    @Test
    public void testMazeRecursiveBackTrackerBlocks() {
        TestMazeMap demoMap = testRecursiveBackTracker(MazeGenerationParams.AlgorithmType.RECURSIVE_BACKTRACKER_BLOCKS);
        testAStar(demoMap);
    }

    @Test
    public void testMazeRecursiveBackTrackerPassages() {
        TestMazeMap demoMap = testRecursiveBackTracker(MazeGenerationParams.AlgorithmType.RECURSIVE_BACKTRACKER_PASSAGES);
        testAStar(demoMap);
    }

    private void testAStar(TestMazeMap demoMap) {
        TestMazeMapWalker walker = mapPartFactory.createWalker();
        TestMazeMapField start = getStartField(demoMap);
        TestMazeMapField end = getEndField(demoMap);
        Assert.assertNotEquals(start, end);
        List<TestMazeMapField> path = demoMap.aStar(start, end, walker, 500);
        Assert.assertFalse(path.isEmpty());
    }

    private TestMazeMap testRecursiveBackTracker(MazeGenerationParams.AlgorithmType algorithmus) {
        TestMazeMap demoMap = mapFactory.createMap(14, 15, MapStyle.HEX_VERTICAL);
        MazeGenerationParams params = new MazeGenerationParams(algorithmus);
        demoMap.generateMaze(params);
        return demoMap;
    }

    private TestMazeMapField getEndField(TestMazeMap demoMap) {
        List<TestMazeMapField> reverted = new ArrayList<>(demoMap.getFields());
        Collections.reverse(reverted);
        for (TestMazeMapField f : reverted) {
            if (!f.getData().isBlocked()) {
                return f;
            }
        }
        return null;
    }

    private TestMazeMapField getStartField(TestMazeMap demoMap) {
        for (TestMazeMapField f : demoMap.getFields()) {
            if (!f.getData().isBlocked()) {
                return f;
            }
        }
        return null;
    }
}
