package com.github.martinfrank.mazelib.map;

import com.github.martinfrank.maplib.Map;
import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.mazelib.MazeGenerationParams;
import com.github.martinfrank.mazelib.algorithm.PerfectMazeAlgorithm;
import com.github.martinfrank.mazelib.algorithm.RecursiveBacktrackerWithBlocks;
import com.github.martinfrank.mazelib.algorithm.RecursiveBacktrackerWithPassages;
import com.github.martinfrank.mazelib.mapdata.MazeMapEdgeData;
import com.github.martinfrank.mazelib.mapdata.MazeMapFieldData;

public abstract class MazeMap<D,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>,
        W extends MazeMapWalker<F, E, N>> extends Map<D, F, E, N, W> {

    public MazeMap(int width, int height, MapStyle style, D d) {
        super(width, height, style, d);
    }

    public void generateMaze(MazeGenerationParams mazeGenerationParams) {
        PerfectMazeAlgorithm<? extends MazeMap, F, E, N> algorithm = null;
        if (mazeGenerationParams.isRecursiveBcktrackerWithPassages()) {
            algorithm = new RecursiveBacktrackerWithPassages<>(this);
        }
        if (mazeGenerationParams.isRecursiveBcktrackerWithBlocks()) {
            algorithm = new RecursiveBacktrackerWithBlocks<>(this);
        }
        if (algorithm != null) {
            algorithm.createPerfectMaze();
        }
    }

}
