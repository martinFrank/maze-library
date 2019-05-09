package com.github.martinfrank.mazelib.algorithm;

import com.github.martinfrank.mazelib.map.MazeMap;
import com.github.martinfrank.mazelib.map.MazeMapEdge;
import com.github.martinfrank.mazelib.map.MazeMapField;
import com.github.martinfrank.mazelib.map.MazeMapNode;
import com.github.martinfrank.mazelib.mapdata.MazeMapEdgeData;
import com.github.martinfrank.mazelib.mapdata.MazeMapFieldData;

public abstract class AbstractAlgorithm<M extends MazeMap<?, F, E, N, ?>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> implements PerfectMazeAlgorithm<M, F, E, N> {

    private final MapAccessor<M, F, E, N> mapAccessor;
    private final M map;
    private final MapCarver<M, F, E, N> carver;


    AbstractAlgorithm(M map) {
        this.map = map;
        mapAccessor = new MapAccessor<>(map);
        this.carver = new MapCarver<>(map);
    }

    @Override
    public MapAccessor<M, F, E, N> getMapAccessor() {
        return mapAccessor;
    }

    @Override
    public M getMap() {
        return map;
    }

    @Override
    public MapCarver<M, F, E, N> getCarver() {
        return carver;
    }

}
