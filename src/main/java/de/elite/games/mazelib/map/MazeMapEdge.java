package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapEdge;

public abstract class MazeMapEdge<D,
        F extends MazeMapField<?, F, E, N>,
        E extends MazeMapEdge<D, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> extends MapEdge<D, F, E, N> {


    public MazeMapEdge(D d) {
        super(d);
    }
}
