package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapPartFactory;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;

public abstract class MazeMapPartFactory<M extends MazeMap<?, F, E, P, W>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, P>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, P>,
        P extends MazeMapPoint<?, F, E, P>,
        W extends MazeMapWalker<F, E, P>> extends MapPartFactory<M, F, E, P, W> {



}


