package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapPartFactory;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;

public abstract class MazeMapPartFactory<M extends MazeMap<?, F, E, P, W>, F extends MazeMapField<?, F, E, P>, E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, P>, P extends MazeMapPoint<?, F, E, P>, W extends MazeMapWalker<F, E, P>> extends MapPartFactory<M, F, E, P, W> {



}


