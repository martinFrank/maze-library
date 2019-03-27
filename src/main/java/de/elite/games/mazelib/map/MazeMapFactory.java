package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapFactory;
import de.elite.games.maplib.MapPartFactory;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;

public class MazeMapFactory<M extends MazeMap<?, F, E, N, W>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>,
        W extends MazeMapWalker<F, E, N>> extends MapFactory<M, F, E, N, W> {

    public MazeMapFactory(MapPartFactory<M, F, E, N, W> mapPartFactory) {
        super(mapPartFactory);
    }
}
