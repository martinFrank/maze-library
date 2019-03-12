package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapPoint;

public abstract class MazeMapPoint<D, F extends MazeMapField<?, F, E, P>, E extends MazeMapEdge<?, F, E, P>, P extends MazeMapPoint<D, F, E, P>> extends MapPoint<D, F, E, P> {

    public MazeMapPoint(int x, int y) {
        super(x, y);
    }
}
