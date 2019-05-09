package com.github.martinfrank.mazelib.algorithm;

import com.github.martinfrank.geolib.GeoPoint;
import com.github.martinfrank.mazelib.map.MazeMap;
import com.github.martinfrank.mazelib.map.MazeMapEdge;
import com.github.martinfrank.mazelib.map.MazeMapField;
import com.github.martinfrank.mazelib.map.MazeMapNode;
import com.github.martinfrank.mazelib.mapdata.MazeMapEdgeData;
import com.github.martinfrank.mazelib.mapdata.MazeMapFieldData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class MapAccessor<M extends MazeMap<?, F, E, N, ?>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> {

    private final M map;

    MapAccessor(M map) {
        this.map = map;
    }

    F getRandomStart() {
        List<F> list = new ArrayList<>(map.getFields());
        Collections.shuffle(list);
        return list.get(0);
    }

    F getRandomStartInBounds(int distanceToBorder) {
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

        Collections.shuffle(inBoundsList);
        return inBoundsList.get(0);
    }

}
