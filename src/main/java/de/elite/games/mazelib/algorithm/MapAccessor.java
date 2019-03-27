package de.elite.games.mazelib.algorithm;

import de.elite.games.geolib.GeoPoint;
import de.elite.games.mazelib.map.MazeMap;
import de.elite.games.mazelib.map.MazeMapEdge;
import de.elite.games.mazelib.map.MazeMapField;
import de.elite.games.mazelib.map.MazeMapNode;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MapAccessor<M extends MazeMap<?, F, E, N, ?>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapAccessor.class);

    private final M map;
    private final Carver<M, F, E, N> carver;

    public MapAccessor(M map) {
        this.map = map;
        this.carver = new Carver<>(map);
    }

    public F getRandomStart() {
        List<F> list = new ArrayList<>(map.getFields());
        Collections.shuffle(list);
        return list.get(0);
    }

    public void carvePassage(F from, F into) {
        Optional<E> edge = from.getEdge(into);
        if (edge.isPresent()) {
            MazeMapEdgeData edgeData = edge.get().getData();
            edgeData.getPassage().setOpen(true);
        }
    }

    public List<F> getNeighborsForPassageCarving(F field, Collection<F> exclusion) {
        List<F> list = field.getFields().stream().filter(f -> !exclusion.contains(f)).collect(Collectors.toList());
        Collections.shuffle(list);
        return list;
    }

    public List<F> getNeighborsForWallCarving(F field, Collection<F> exclusion) {
        List<F> list = field.getFields().stream().
                filter(carver.getCarvingFilter(field)).
                filter(getExclusionFilter(exclusion)).
                collect(Collectors.toList());
        Collections.shuffle(list);
        return list;
    }

    private Predicate<F> getExclusionFilter(Collection<F> exclusion) {
        return f -> !exclusion.contains(f);
    }


    public void carveInto(F from, F into) {
        into.getData().setBlocked(false);
        carvePassage(from, into);
    }

    public F getRandomStartInBounds(int i) {
        Predicate<F> boundsFilter = getBoundsFilter(i, map.getColumns(), map.getRows());
        List<F> inBoundsList = map.getFields().stream().filter(boundsFilter).collect(Collectors.toList());
        Collections.shuffle(inBoundsList);
        return inBoundsList.get(0);
    }

    private Predicate<F> getBoundsFilter(int i, int w, int h) {
        return f -> {
            GeoPoint index = f.getIndex();
            return index.getX() >= i && index.getY() >= i && index.getX() < w - 1 && index.getY() < h - 1;
        };
    }
}
