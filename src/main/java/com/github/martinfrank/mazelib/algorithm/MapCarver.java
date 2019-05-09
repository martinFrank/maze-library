package com.github.martinfrank.mazelib.algorithm;

import com.github.martinfrank.mazelib.map.MazeMap;
import com.github.martinfrank.mazelib.map.MazeMapEdge;
import com.github.martinfrank.mazelib.map.MazeMapField;
import com.github.martinfrank.mazelib.map.MazeMapNode;
import com.github.martinfrank.mazelib.mapdata.MazeMapEdgeData;
import com.github.martinfrank.mazelib.mapdata.MazeMapFieldData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


class MapCarver<M extends MazeMap<?, F, E, N, ?>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> {

    private final M map;

    MapCarver(M map) {
        this.map = map;
    }

    List<F> getNeighborsForPassageCarving(F field, Collection<F> exclusion) {
        List<F> list = new ArrayList<>();
        for (F f : field.getFields()) {
            if (!exclusion.contains(f)) {
                list.add(f);
            }
        }
        Collections.shuffle(list);
        return list;
    }

    List<F> getNeighborsForWallCarving(F field, Collection<F> exclusion) {
        List<F> list = new ArrayList<>();
        for (F f : field.getFields()) {
            if (canCarveInto(field, f) && !exclusion.contains(f)) {
                list.add(f);
            }
        }
        Collections.shuffle(list);
        return list;
    }

    void carvePassage(F from, F into) {
        E edge = from.getEdge(into);
        if (edge != null) {
            MazeMapEdgeData edgeData = edge.getData();
            edgeData.getPassage().setOpen(true);
        }
    }

    void carveInto(F into) {
        into.getData().setBlocked(false);
    }

    void carveInto(F from, F into) {
        into.getData().setBlocked(false);
        carvePassage(from, into);
    }


    boolean canCarveInto(F from, F into) {
        List<F> targetFields = getFieldsAroundTarget(from, into);
        return isCarvingAllowed(targetFields);
    }

    private boolean isCarvingAllowed(List<F> rest) {
        for (F f : rest) {
            if (!f.getData().isBlocked()) {
                return false;
            }
        }
        return true;
    }

    private List<F> getFieldsAroundTarget(F from, F into) {
        switch (map.getStyle()) {
            case TRIANGLE_HORIZONTAL:
                return getFieldsAroundTargetTriangle(from, into);
            case TRIANGLE_VERTICAL:
                return getFieldsAroundTargetTriangle(from, into);
            case HEX_HORIZONTAL:
                return getFieldsAroundTargetForHex(from, into);
            case HEX_VERTICAL:
                return getFieldsAroundTargetForHex(from, into);
            case SQUARE:
                return getFieldsAroundTargetForSquares(from, into);
            case SQUARE_ISOMETRIC:
                return getFieldsAroundTargetForSquares(from, into);
            case SQUARE_DIAMOND:
                return getFieldsAroundTargetForSquares(from, into);
            default:
                return getFieldsAroundTargetForSquares(from, into);
        }
    }

    private List<F> getFieldsAroundTargetTriangle(F from, F into) {
        List<N> nodes = new ArrayList<>(into.getNodes());
        E edge = into.getEdge(from);
        if (edge != null) {
            nodes.remove(edge.getNodes().get(0));
            nodes.remove(edge.getNodes().get(1));
        }
        if (nodes.size() == 1) {
            List<F> fields = new ArrayList<>(nodes.get(0).getFields());
            List<F> list = new ArrayList<>();
            for (F f : fields) {
                if (!f.equals(from)) {
                    list.add(f);
                }
            }
            return list;
        }
        return Collections.emptyList();

    }

    private List<F> getFieldsAroundTargetForHex(F from, F into) {
        List<F> fields = into.getFields();
        List<F> list = new ArrayList<>();
        for (F f : fields) {
            if (!f.equals(from)) {
                list.add(f);
            }
        }
        return list;
    }

    private List<F> getFieldsAroundTargetForSquares(F from, F into) {
        List<F> fields = new ArrayList<>();
        for (N n : into.getNodes()) {
            fields.addAll(n.getFields());
        }
        fields.addAll(into.getFields());
        fields.removeAll(from.getFields());
        List<F> list = new ArrayList<>();
        for (F f : fields) {
            if (!f.equals(from)) {
                list.add(f);
            }
        }
        return list;
    }

}
