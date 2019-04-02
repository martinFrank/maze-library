package de.elite.games.mazelib.algorithm;

import com.github.martinfrank.geolib.GeoPoint;
import de.elite.games.mazelib.map.MazeMap;
import de.elite.games.mazelib.map.MazeMapEdge;
import de.elite.games.mazelib.map.MazeMapField;
import de.elite.games.mazelib.map.MazeMapNode;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;

class MapAccessor<M extends MazeMap<?, F, E, N, ?>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> {

//    private static final Logger LOGGER = LoggerFactory.getLogger(MapAccessor.class);

    private final M map;
    private final MapCarver<M, F, E, N> carver;

    MapAccessor(M map) {
        this.map = map;
        this.carver = new MapCarver<>(map);
    }

    F getRandomStart() {
        List<F> list = new ArrayList<>(map.getFields());
        Collections.shuffle(list);
        return list.get(0);
    }

    F getRandomStartInBounds(int distanceToBorder) {
//        Predicate<F> boundsFilter = getBoundsFilter(distanceToBorder, map.getColumns(), map.getRows());

        List<F> inBoundsList = new ArrayList<>();
        for (F f : map.getFields()) {
            GeoPoint index = f.getIndex();
            if (index.getX() >= distanceToBorder &&
                    index.getY() >= distanceToBorder &&
                    index.getX() < map.getColumns() - 1 &&
                    index.getY() < map.getRows() - 1) {
                inBoundsList.add(f);
            }
        }

//        List<F> inBoundsList = map.getFields().stream().filter(boundsFilter).collect(Collectors.toList());
        Collections.shuffle(inBoundsList);
        return inBoundsList.get(0);
    }

//    private Predicate<F> getBoundsFilter(int distanceToBorder, int w, int h) {
//        return f -> {
//            GeoPoint index = f.getIndex();
//            return index.getX() >= distanceToBorder &&
//                    index.getY() >= distanceToBorder &&
//                    index.getX() < w - 1 &&
//                    index.getY() < h - 1;
//        };
//    }
}
