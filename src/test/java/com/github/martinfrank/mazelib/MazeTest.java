package com.github.martinfrank.mazelib;

import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.mazelib.map.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeTest {

    @Test
    public void testMaze() {
        TestMazeMapPartFactory mapPartFactory = new TestMazeMapPartFactory();
        TestMazeMapFactory mapFactory = new TestMazeMapFactory(mapPartFactory);
        TestMazeMap demoMap = mapFactory.createMap(14, 15, MapStyle.HEX_VERTICAL);
        demoMap.scale(11f);
        MazeGenerationParams params = new MazeGenerationParams(MazeGenerationParams.AlgorithmType.RECURSIVE_BACKTRACKER_BLOCKS);
        demoMap.generateMaze(params);

        TestMazeMapWalker walker = mapPartFactory.createWalker();
        TestMazeMapField start = getStartField(demoMap);
        TestMazeMapField end = getEndField(demoMap);
        Assert.assertNotEquals(start, end);
        List<TestMazeMapField> path = demoMap.aStar(start, end, walker, 500);
        Assert.assertFalse(path.isEmpty());

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
