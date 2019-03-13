package de.elite.games.mazelib.map;

import de.elite.games.geolib.GeoPoint;
import de.elite.games.maplib.MapField;

public abstract class MazeMapField<D, F extends MazeMapField<D, F, E, P>, E extends MazeMapEdge<?, F, E, P>, P extends MazeMapPoint<?, F, E, P>> extends MapField<D, F, E, P> {

    public MazeMapField(GeoPoint index, D d) {
        super(index, d);
    }
}
