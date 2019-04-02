package de.elite.games.mazelib.algorithm;

import de.elite.games.mazelib.map.MazeMap;
import de.elite.games.mazelib.map.MazeMapEdge;
import de.elite.games.mazelib.map.MazeMapField;
import de.elite.games.mazelib.map.MazeMapNode;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;

class MapCarver<M extends MazeMap<?, F, E, N, ?>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> {

//    private static final Logger LOGGER = LoggerFactory.getLogger(MapCarver.class);

    private final M map;

    MapCarver(M map) {
        this.map = map;
    }

    List<F> getNeighborsForPassageCarving(F field, Collection<F> exclusion) {
//        List<F> list = field.getFields().stream().filter(f -> !exclusion.contains(f)).collect(Collectors.toList());
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
//        List<F> list = field.getFields().stream().
//                filter(getCarvingFilter(field)).
//                filter(getExclusionFilter(exclusion)).
//                collect(Collectors.toList());

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
//        Optional<E> edge = from.getEdge(into);
        E edge = from.getEdge(into);
        if (edge != null) {
//            MazeMapEdgeData edgeData = edge.get().getData();
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

//    Predicate<F> getCarvingFilter(F from) {
//        return into -> {
//            List<Optional<F>> targetFields = getFieldsAroundTarget(from, into);
//            return isCarvingAllowed(targetFields);
//        };
//
//    }

    boolean canCarveInto(F from, F into) {

//        return into -> {
//            List<Optional<F>> targetFields = getFieldsAroundTarget(from, into);
//            return isCarvingAllowed(targetFields);
//        };
        List<F> targetFields = getFieldsAroundTarget(from, into);
        return isCarvingAllowed(targetFields);

    }

//    private boolean isCarvingAllowed(List<Optional<F>> rest) {
//        for (Optional<F> f : rest) {
//            if (f.isPresent() && !f.get().getData().isBlocked()) {
//                return false;
//            }
//        }
//        return true;
//    }

    private boolean isCarvingAllowed(List<F> rest) {
        for (F f : rest) {
            if (!f.getData().isBlocked()) {
                return false;
            }
        }
        return true;
    }

    //    private List<Optional<F>> getFieldsAroundTarget(F from, F into) {
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

    //    private List<Optional<F>> getFieldsAroundTargetTriangle(F from, F into) {
    private List<F> getFieldsAroundTargetTriangle(F from, F into) {
        List<N> nodes = new ArrayList<>(into.getNodes());
        E edge = into.getEdge(from);
        if (edge != null) {
//            nodes.remove(edge.get().getNodes().get(0));
//            nodes.remove(edge.get().getNodes().get(1));
            nodes.remove(edge.getNodes().get(0));
            nodes.remove(edge.getNodes().get(1));
        }
//        LOGGER.debug("size of nodes {}", nodes.size());
        if (nodes.size() == 1) {
            List<F> fields = new ArrayList<>(nodes.get(0).getFields());
//            List<Optional<F>> list = new ArrayList<>();
            List<F> list = new ArrayList<>();
//            fields.forEach(f -> {
//                if (!f.equals(from)) {
//                    list.add(Optional.of(f));
//                }
//            });
            for (F f : fields) {
                if (!f.equals(from)) {
                    list.add(f);
                }
            }
            return list;
        }
        return Collections.emptyList();

    }

    //    private List<Optional<F>> getFieldsAroundTargetForHex(F from, F into) {
    private List<F> getFieldsAroundTargetForHex(F from, F into) {
        List<F> fields = into.getFields();
//        List<Optional<F>> list = new ArrayList<>();
        List<F> list = new ArrayList<>();
//        fields.forEach(f -> {
//            if (!f.equals(from)) {
//                list.add(Optional.of(f));
//            }
//        });
        for (F f : fields) {
            if (!f.equals(from)) {
                list.add(f);
            }
        }
        return list;
    }

    //    private List<Optional<F>> getFieldsAroundTargetForSquares(F from, F into) {
    private List<F> getFieldsAroundTargetForSquares(F from, F into) {
        List<F> fields = new ArrayList<>();
//        into.getNodes().forEach(n -> fields.addAll(n.getFields()));
        for (N n : into.getNodes()) {
            fields.addAll(n.getFields());
        }
        fields.addAll(into.getFields());
        fields.removeAll(from.getFields());
//        List<Optional<F>> list = new ArrayList<>();
        List<F> list = new ArrayList<>();
//        fields.forEach(f -> {
//            if (!f.equals(from)) {
//                list.add(Optional.of(f));
//            }
//        });
        for (F f : fields) {
            if (!f.equals(from)) {
                list.add(f);
            }
        }
        return list;
    }

//    private Predicate<F> getExclusionFilter(Collection<F> exclusion) {
//        return f -> !exclusion.contains(f);
//    }
}
