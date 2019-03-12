package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapWalker;

public abstract class MazeMapWalker<F extends MazeMapField<?, F, E, P>, E extends MazeMapEdge<?, F, E, P>, P extends MazeMapPoint<?, F, E, P>> extends MapWalker<F, E, P> {

}
