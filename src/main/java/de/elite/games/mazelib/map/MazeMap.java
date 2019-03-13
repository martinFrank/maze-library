package de.elite.games.mazelib.map;

import de.elite.games.maplib.Map;
import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.MazeGenerationParams;
import de.elite.games.mazelib.algorithm.RecursiveBackTracker;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;

public abstract class MazeMap<D, F extends MazeMapField<?, F, E, P>, E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, P>, P extends MazeMapPoint<?, F, E, P>, W extends MazeMapWalker<F, E, P>> extends Map<D, F, E, P, W> {

    public MazeMap(int width, int height, MapStyle style) {
        super(width, height, style);
    }

    public void generateMaze(MazeGenerationParams mazeGenerationParams) {
        RecursiveBackTracker<MazeMap<D, F, E, P, W>, F, E, P> recursiveBackTracker = new RecursiveBackTracker<>();
        recursiveBackTracker.createPerfectMaze(this);
    }
}
