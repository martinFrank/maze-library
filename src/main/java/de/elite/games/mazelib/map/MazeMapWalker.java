package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapWalker;

public abstract class MazeMapWalker<F extends MazeMapField<?, F, E, N>,
        E extends MazeMapEdge<?, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> extends MapWalker<F, E, N> {

}
