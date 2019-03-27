package de.elite.games.mazelib.map;

import de.elite.games.maplib.Map;
import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.MazeGenerationParams;
import de.elite.games.mazelib.algorithm.PerfectMazeAlgorithm;
import de.elite.games.mazelib.algorithm.RecursiveBacktrackerWithBlocks;
import de.elite.games.mazelib.algorithm.RecursiveBacktrackerWithPassages;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;

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

//    private PerfectMazeAlgorithm<? extends MazeMap,F,E,N> getAlgorithm(MazeGenerationParams mazeGenerationParams) {
//        if (mazeGenerationParams.isRecursiveBcktrackerWithPassages()) {
//            return new RecursiveBacktrackerWithPassages<>(this);
//        }
//        if (mazeGenerationParams.isKruskals()) {
//            return new Kruskals<>(this);
//        }
//        return null;
//    }
}
