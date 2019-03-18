package de.elite.games.mazelib.map;

import de.elite.games.maplib.Map;
import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.MazeGenerationParams;
import de.elite.games.mazelib.algorithm.PerfectMazeAlgorithm;
import de.elite.games.mazelib.algorithm.RecursiveBacktracker;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;

public abstract class MazeMap<D, F extends MazeMapField<? extends MazeMapFieldData, F, E, P>, E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, P>, P extends MazeMapPoint<?, F, E, P>, W extends MazeMapWalker<F, E, P>> extends Map<D, F, E, P, W> {

    public MazeMap(int width, int height, MapStyle style, D d) {
        super(width, height, style, d);
    }

    public void generateMaze(MazeGenerationParams mazeGenerationParams) {
        PerfectMazeAlgorithm<? extends MazeMap, F, E, P> algorithm = null;
        if (mazeGenerationParams.isRecursiveBcktracker()) {
            algorithm = new RecursiveBacktracker<>(this);
        }
        if (algorithm != null) {
            algorithm.createPerfectMaze();
        }
    }

//    private PerfectMazeAlgorithm<? extends MazeMap,F,E,P> getAlgorithm(MazeGenerationParams mazeGenerationParams) {
//        if (mazeGenerationParams.isRecursiveBcktracker()) {
//            return new RecursiveBacktracker<>(this);
//        }
//        if (mazeGenerationParams.isKruskals()) {
//            return new Kruskals<>(this);
//        }
//        return null;
//    }
}
