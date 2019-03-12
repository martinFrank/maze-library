package de.elite.games.mazelib.map;

import de.elite.games.maplib.Map;
import de.elite.games.maplib.MapStyle;

public abstract class MazeMap<D, F extends MazeMapField<?, F, E, P>, E extends MazeMapEdge<?, F, E, P>, P extends MazeMapPoint<?, F, E, P>, W extends MazeMapWalker<F, E, P>> extends Map<D, F, E, P, W> {

    public MazeMap(int width, int height, MapStyle style) {
        super(width, height, style);
    }

}
