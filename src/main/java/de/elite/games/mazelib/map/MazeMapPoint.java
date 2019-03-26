package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapNode;

public abstract class MazeMapPoint<D,
        F extends MazeMapField<?, F, E, P>,
        E extends MazeMapEdge<?, F, E, P>,
        P extends MazeMapPoint<D, F, E, P>> extends MapNode<D, F, E, P> {

    public MazeMapPoint(D d) {
        super(d);
    }
}
