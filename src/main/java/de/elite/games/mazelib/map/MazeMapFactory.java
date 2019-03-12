package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapFactory;
import de.elite.games.maplib.MapPartFactory;

public class MazeMapFactory<M extends MazeMap<?, F, E, P, W>, F extends MazeMapField<?, F, E, P>, E extends MazeMapEdge<?, F, E, P>, P extends MazeMapPoint<?, F, E, P>, W extends MazeMapWalker<F, E, P>> extends MapFactory<M, F, E, P, W> {

    public MazeMapFactory(MapPartFactory<M, F, E, P, W> mapPartFactory) {
        super(mapPartFactory);
    }
}
