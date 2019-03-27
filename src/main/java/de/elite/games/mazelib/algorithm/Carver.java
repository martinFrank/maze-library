package de.elite.games.mazelib.algorithm;

import de.elite.games.mazelib.map.MazeMap;
import de.elite.games.mazelib.map.MazeMapEdge;
import de.elite.games.mazelib.map.MazeMapField;
import de.elite.games.mazelib.map.MazeMapNode;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Carver<M extends MazeMap<?, F, E, N, ?>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Carver.class);

    private final M map;

    public Carver(M map) {
        this.map = map;
    }

    public Predicate<F> getCarvingFilter(F from) {
        return into -> {
            List<Optional<F>> targetFields = getFieldsAroundTarget(from, into);
            if (!isCarvingAllowed(targetFields)) {
                return false;
            }
            return true;
        };

    }

    private boolean isCarvingAllowed(List<Optional<F>> rest) {
        for (Optional<F> f : rest) {
            if (f.isPresent() && !f.get().getData().isBlocked()) {
                return false;
            }
        }
        return true;
    }

    private List<Optional<F>> getFieldsAroundTarget(F from, F into) {
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
                return getFieldsAroundTargetForSquare(from, into);
            case SQUARE_ISOMETRIC:
                return getFieldsAroundTargetForDiamond(from, into);
            case SQUARE_DIAMOND:
                return getFieldsAroundTargetForDiamond(from, into);
            default:
                return getFieldsAroundTargetForSquare(from, into);
        }
    }

    private List<Optional<F>> getFieldsAroundTargetTriangle(F from, F into) {
        List<N> nodes = new ArrayList<>(into.getNodes());
        Optional<E> edge = into.getEdge(from);
        if (edge.isPresent()) {
            nodes.remove(edge.get().getNodes().get(0));
            nodes.remove(edge.get().getNodes().get(1));
        }
        LOGGER.debug("size of nodes {}", nodes.size());
        if (nodes.size() == 1) {
            List<F> fields = new ArrayList<>(nodes.get(0).getFields());
            List<Optional<F>> list = new ArrayList<>();
            fields.forEach(f -> {
                if (!f.equals(from)) {
                    list.add(Optional.of(f));
                }
            });
            return list;
        }

        return Collections.emptyList();

    }


    private List<Optional<F>> getFieldsAroundTargetForHex(F from, F into) {
        List<F> fields = into.getFields();
        List<Optional<F>> list = new ArrayList<>();
        fields.forEach(f -> {
            if (!f.equals(from)) {
                list.add(Optional.of(f));
            }
        });
        return list;
    }

    private List<Optional<F>> getFieldsAroundTargetForDiamond(F from, F into) {

//        List<F> fields = new ArrayList<>();
//        into.getNodes().forEach(n -> fields.addAll(n.getFields()) );
//        fields.addAll(into.getFields());
//        fields.removeAll(from.getFields());
//        List<Optional<F>> list = new ArrayList<>();
//        fields.forEach(f -> {
//            if (!f.equals(from)){
//                list.add(Optional.of(f));
//            }
//        });
//        return list;

//
        Direction dir = Direction.get(from.getIndex(), into.getIndex());
        List<Optional<F>> list = new ArrayList<>();
        int cx = into.getIndex().getX();
        int cy = into.getIndex().getY();
        if (dir == Direction.SE) { //ok
            list.add(map.getField(cx, cy - 1));
            list.add(map.getField(cx + 1, cy));
            list.add(map.getField(cx, cy + 1));
            list.add(map.getField(cx, cy + 2));
            list.add(map.getField(cx - 1, cy + 1));
            return list;
        }
        if (dir == Direction.NW) { //ok
            list.add(map.getField(cx + 1, cy - 1));
            list.add(map.getField(cx, cy - 2));
            list.add(map.getField(cx, cy - 1));
            list.add(map.getField(cx - 1, cy));
            list.add(map.getField(cx, cy + 1));
            return list;
        }
        if (dir == Direction.N) { //ok
            if (cy % 2 == 0) {
                list.add(map.getField(cx, cy - 1));
                list.add(map.getField(cx, cy - 2));
                list.add(map.getField(cx - 1, cy - 1));
                list.add(map.getField(cx - 1, cy));
                list.add(map.getField(cx - 1, cy + 1));
                return list;
            } else {
                list.add(map.getField(cx, cy - 1));
                list.add(map.getField(cx, cy - 2));
                list.add(map.getField(cx + 1, cy - 1));
                list.add(map.getField(cx + 1, cy));
                list.add(map.getField(cx + 1, cy + 1));
                return list;
            }
        }
        if (dir == Direction.S) {//ok
            if (cy % 2 == 0) {
                list.add(map.getField(cx - 1, cy - 1));
                list.add(map.getField(cx - 1, cy));
                list.add(map.getField(cx - 1, cy + 1));
                list.add(map.getField(cx, cy + 2));
                list.add(map.getField(cx, cy + 1));
                return list;
            } else {
                list.add(map.getField(cx + 1, cy - 1));
                list.add(map.getField(cx + 1, cy));
                list.add(map.getField(cx + 1, cy + 1));
                list.add(map.getField(cx, cy + 2));
                list.add(map.getField(cx, cy + 1));
                return list;
            }
        }
        if (dir == Direction.NE) {//ok
            list.add(map.getField(cx - 1, cy - 1));
            list.add(map.getField(cx, cy - 2));
            list.add(map.getField(cx, cy - 1));
            list.add(map.getField(cx + 1, cy));
            list.add(map.getField(cx, cy + 1));
            return list;
        }
        if (dir == Direction.SW) {
//            if (cy%2==0) {
//
//            }else {
            list.add(map.getField(cx, cy - 1));
            list.add(map.getField(cx - 1, cy));
            list.add(map.getField(cx, cy + 1));
            list.add(map.getField(cx, cy + 2));
            list.add(map.getField(cx + 1, cy + 1));
            return list;
//            }
        }
        return list;
    }

    private List<Optional<F>> getFieldsAroundTargetForSquare(F from, F into) {
        Direction dir = Direction.get(from.getIndex(), into.getIndex());
        List<Optional<F>> list = new ArrayList<>();
        int cx = into.getIndex().getX();
        int cy = into.getIndex().getY();
        if (dir == Direction.N) {
            list.add(map.getField(cx - 1, cy));
            list.add(map.getField(cx - 1, cy - 1));
            list.add(map.getField(cx, cy - 1));
            list.add(map.getField(cx + 1, cy - 1));
            list.add(map.getField(cx + 1, cy));
            return list;
        }
        if (dir == Direction.E) {
            list.add(map.getField(cx, cy - 1));
            list.add(map.getField(cx + 1, cy - 1));
            list.add(map.getField(cx + 1, cy));
            list.add(map.getField(cx + 1, cy + 1));
            list.add(map.getField(cx, cy + 1));
            return list;
        }
        if (dir == Direction.S) {
            list.add(map.getField(cx + 1, cy));
            list.add(map.getField(cx + 1, cy + 1));
            list.add(map.getField(cx, cy + 1));
            list.add(map.getField(cx - 1, cy + 1));
            list.add(map.getField(cx - 1, cy));
            return list;
        }
        if (dir == Direction.W) {
            list.add(map.getField(cx, cy + 1));
            list.add(map.getField(cx - 1, cy + 1));
            list.add(map.getField(cx - 1, cy));
            list.add(map.getField(cx - 1, cy - 1));
            list.add(map.getField(cx, cy - 1));
            return list;
        }
        return list;
    }
}
