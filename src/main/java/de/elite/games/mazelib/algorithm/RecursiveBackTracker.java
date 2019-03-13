package de.elite.games.mazelib.algorithm;

import de.elite.games.mazelib.map.MazeMap;
import de.elite.games.mazelib.map.MazeMapEdge;
import de.elite.games.mazelib.map.MazeMapField;
import de.elite.games.mazelib.map.MazeMapPoint;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class RecursiveBackTracker<M extends MazeMap<?, F, E, P, ?>, F extends MazeMapField<?, F, E, P>, E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, P>, P extends MazeMapPoint<?, F, E, P>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecursiveBackTracker.class);

    public void createPerfectMaze(M map) {
        Stack<F> stack = new Stack<>();
        F current = getRandomStart(map);
        Set<F> closed = new HashSet<>();
        closed.add(current);
        do {
            LOGGER.debug("current: " + current.getIndex());
            List<F> nbgs = getNeighbors(current, closed);
            if (nbgs.isEmpty()) {
                current = stack.pop();
            } else {
                F next = nbgs.get(0);
                carve(current, next);
                stack.push(current);
                current = next;
                closed.add(current);
            }
        } while (!stack.isEmpty());


    }

    private void carve(F from, F to) {
        Optional<E> edge = from.getEdge(to);
        if (edge.isPresent()) {
            MazeMapEdgeData edgeData = edge.get().getData();
            edgeData.getPassage().setOpen(true);
        }
    }

    private List<F> getNeighbors(F field, Set<F> closed) {
        List<F> list = field.getFields().stream().filter(f -> !closed.contains(f)).collect(Collectors.toList());
        Collections.shuffle(list);
        return list;
    }

    private F getRandomStart(M map) {
        List<F> list = new ArrayList<>(map.getFields());
        Collections.shuffle(list);
        return list.get(0);
    }
}
