package de.elite.games.mazelib.algorithm;

import de.elite.games.mazelib.map.MazeMap;
import de.elite.games.mazelib.map.MazeMapEdge;
import de.elite.games.mazelib.map.MazeMapField;
import de.elite.games.mazelib.map.MazeMapPoint;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;

public abstract class AbstractAlgorithm<M extends MazeMap<?, F, E, P, ?>, F extends MazeMapField<? extends MazeMapFieldData, F, E, P>, E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, P>, P extends MazeMapPoint<?, F, E, P>> implements PerfectMazeAlgorithm<M, F, E, P> {

    private final MapAccessor<M, F, E, P> mapAccessor;

    public AbstractAlgorithm(M map) {
        mapAccessor = new MapAccessor<>(map);
    }

    @Override
    public MapAccessor<M, F, E, P> getMapAccessor() {
        return mapAccessor;
    }

}
