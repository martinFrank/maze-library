package de.elite.games.mazelib.algorithm;

import de.elite.games.mazelib.map.MazeMap;
import de.elite.games.mazelib.map.MazeMapEdge;
import de.elite.games.mazelib.map.MazeMapField;
import de.elite.games.mazelib.map.MazeMapNode;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;

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
