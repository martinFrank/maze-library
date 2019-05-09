package com.github.martinfrank.mazelib.map;

import com.github.martinfrank.maplib.MapPartFactory;
import com.github.martinfrank.mazelib.mapdata.MazeMapEdgeData;
import com.github.martinfrank.mazelib.mapdata.MazeMapFieldData;

public abstract class MazeMapPartFactory<M extends MazeMap<?, F, E, N, W>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>,
        W extends MazeMapWalker<F, E, N>> extends MapPartFactory<M, F, E, N, W> {



}


