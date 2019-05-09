package com.github.martinfrank.mazelib.algorithm;

import com.github.martinfrank.mazelib.map.MazeMap;
import com.github.martinfrank.mazelib.map.MazeMapEdge;
import com.github.martinfrank.mazelib.map.MazeMapField;
import com.github.martinfrank.mazelib.map.MazeMapNode;
import com.github.martinfrank.mazelib.mapdata.MazeMapEdgeData;
import com.github.martinfrank.mazelib.mapdata.MazeMapFieldData;

public interface PerfectMazeAlgorithm<M extends MazeMap<?, F, E, N, ?>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> {

    void createPerfectMaze();

    MapAccessor<M, F, E, N> getMapAccessor();

    M getMap();

    MapCarver<M, F, E, N> getCarver();
}
