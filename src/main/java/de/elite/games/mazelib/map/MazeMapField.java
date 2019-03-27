package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapField;

public abstract class MazeMapField<D,
        F extends MazeMapField<D, F, E, N>,
        E extends MazeMapEdge<?, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> extends MapField<D, F, E, N> {

    public MazeMapField(D d) {
        super(d);
    }
}
