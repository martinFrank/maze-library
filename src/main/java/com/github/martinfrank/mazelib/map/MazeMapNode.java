package com.github.martinfrank.mazelib.map;

import com.github.martinfrank.maplib.MapNode;

public abstract class MazeMapNode<D,
        F extends MazeMapField<?, F, E, N>,
        E extends MazeMapEdge<?, F, E, N>,
        N extends MazeMapNode<D, F, E, N>> extends MapNode<D, F, E, N> {

    public MazeMapNode(D d) {
        super(d);
    }
}
