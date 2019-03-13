package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapEdge;

public abstract class MazeMapEdge<D, F extends MazeMapField<?, F, E, P>, E extends MazeMapEdge<D, F, E, P>, P extends MazeMapPoint<?, F, E, P>> extends MapEdge<D, F, E, P> {


    public MazeMapEdge(P a, P b) {
        super(a, b);
    }
}
