package de.elite.games.mazelib.algorithm;

import de.elite.games.mazelib.map.MazeMap;
import de.elite.games.mazelib.map.MazeMapEdge;
import de.elite.games.mazelib.map.MazeMapField;
import de.elite.games.mazelib.map.MazeMapPoint;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;

import java.util.*;
import java.util.stream.Collectors;

public class MapAccessor<M extends MazeMap<?, F, E, P, ?>, F extends MazeMapField<? extends MazeMapFieldData, F, E, P>, E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, P>, P extends MazeMapPoint<?, F, E, P>> {

    private final M map;

    public MapAccessor(M map) {
        this.map = map;
    }

    public F getRandomStart() {
        List<F> list = new ArrayList<>(map.getFields());
        Collections.shuffle(list);
        return list.get(0);
    }

    public void carve(F from, F into) {
        Optional<E> edge = from.getEdge(into);
        if (edge.isPresent()) {
            MazeMapEdgeData edgeData = edge.get().getData();
            edgeData.getPassage().setOpen(true);
        }
    }

    public List<F> getNeighbors(F field, Collection<F> exclusion) {
        List<F> list = field.getFields().stream().filter(f -> !exclusion.contains(f)).collect(Collectors.toList());
        Collections.shuffle(list);
        return list;
    }

    public M getMap() {
        return map;
    }
}
